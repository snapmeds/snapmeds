package com.dosage;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Pattern;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;

import com.utilities.Frequency;

public class CalendarReminder extends Reminder {

	private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static Pattern emailPattern;
	public Long calendarID;
	public List<Long> eventIds;

	static {
		emailPattern = Pattern.compile(EMAIL_REGEX);
	}

	/**
	 * Construct a calendar reminder
	 * 
	 * @param frequency
	 *            - Definition of the recurring nature of the reminder
	 * @param title
	 *            - Primary text of calendar event
	 * @param detail
	 *            - Secondary text of calendar event
	 */
	public CalendarReminder(String title, String detail) {
		super(title, detail);
		calendarID = null;
		eventIds = new ArrayList<Long>();
	}

	@Override
	public void setupReminder(Context context) {
		if (frequency == null)
			return;
		String rrule = frequencyToRRule(frequency);
		for (Long time : frequency.getTimes()) {
			eventIds.add(createEvent(time, rrule, context));
		}

		this.isSetup = true;
	}

	@Override
	public void cancelReminder(Context context) {
		if (!this.isSetup)
			return;
		ContentResolver cr = context.getContentResolver();
		for (Long eventId : eventIds) {
			Uri deleteUri = ContentUris.withAppendedId(Events.CONTENT_URI,
					eventId);
			cr.delete(deleteUri, null, null);
		}
		this.isSetup = false;
	}

	/**
	 * Get the calendar ID of the main calendar (user's personal calendar)
	 */
	private long getCalendarID(Context context) {
		if (calendarID != null) {
			return calendarID;
		}
		long calID = 0;
		Cursor cur = null;
		ContentResolver cr = context.getContentResolver();
		Uri uri = Calendars.CONTENT_URI;
		String[] projection = new String[] { CalendarContract.Calendars._ID,
				CalendarContract.Calendars.ACCOUNT_NAME };
		cur = cr.query(uri, projection, null, null, null);
		while (cur.moveToNext()) {
			String accountName = null;
			// Get the field values
			calID = cur.getLong(0);
			accountName = cur.getString(1);
			if (accountName != null
					&& emailPattern.matcher(accountName).matches()) {
				calendarID = calID;
				return calID;
			}
		}
		calendarID = null;
		return -1;
	}

	private long createEvent(long milliseconds, String rrule, Context context) {
		long calID = getCalendarID(context);
		ContentResolver cr = context.getContentResolver();

		ContentValues values = new ContentValues();
		values.put(Events.CALENDAR_ID, calID);
		values.put(Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());
		values.put(Events.DTSTART, milliseconds);
		values.put(Events.DTEND, milliseconds);
		values.put(Events.TITLE, title);
		values.put(Events.RRULE, rrule);
		values.put(Events.DESCRIPTION, detail);
		values.put(Events.ACCESS_LEVEL, Events.ACCESS_PRIVATE);
		values.put(Events.AVAILABILITY, Events.AVAILABILITY_FREE);
		Uri uri = cr.insert(Events.CONTENT_URI, values);

		// Get the eventID of the event we just created
		long eventID = Long.parseLong(uri.getLastPathSegment());

		return eventID;
	}

	private String frequencyToRRule(Frequency frequency) {
		String rrule = "FREQ=";
		rrule += frequency.getUnit().toString();
		if (frequency.getInterval() > 0) {
			rrule += ";INTERVAL=" + frequency.getInterval();
		}
		return rrule;
	}

	/* ----------- Functions only for Jackson serialization ------------- */

	public List<Long> getEventIds() {
		return eventIds;
	}

	public void setEventIds(List<Long> eventIds) {
		this.eventIds = eventIds;
	}

	public CalendarReminder() {
	}
}
