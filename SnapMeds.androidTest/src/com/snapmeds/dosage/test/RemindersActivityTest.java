package com.snapmeds.dosage.test;

import java.util.Calendar;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.CalendarContract.Events;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.dosage.CalendarReminder;
import com.dosage.RemindersActivity;
import com.snapmeds.R;
import com.utilities.Dosage;
import com.utilities.Drug;
import com.utilities.Frequency;
import com.utilities.FrequencyUnit;
import com.utilities.Prescription;

public class RemindersActivityTest extends ActivityInstrumentationTestCase2<RemindersActivity> {

    private RemindersActivity activity;
    private CheckBox notifications;
    private CheckBox calendarEvents;
    private Button editTimes;
    private Button apply;
    private TextView title;
    private Drug drug;
    private Dosage dosage;
    private Frequency frequency;
    private CalendarReminder calReminder;

    public RemindersActivityTest() {
        super( RemindersActivity.class );
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode( false );

        activity = getActivity();

        Prescription prescription = new Prescription();
        drug = new Drug();
        drug.setName( "Tylenol" );
        prescription.setDrug( drug );
        activity.prescription = prescription;

        frequency = new Frequency( 1, FrequencyUnit.DAILY, 0 );
        calReminder = new CalendarReminder( drug.getName(), "One pill" );

        dosage = new Dosage();
        dosage.setFrequency( frequency );
        calReminder.setFrequency( frequency );
        dosage.addReminder( calReminder );

        title = (TextView) activity.findViewById( R.id.dosage_info );
        notifications = (CheckBox) activity.findViewById( R.id.notifications );
        calendarEvents = (CheckBox) activity.findViewById( R.id.calendar_events );
        editTimes = (Button) activity.findViewById( R.id.editTimesButton );
        apply = (Button) activity.findViewById( R.id.applyButton );
    }
    
    @UiThreadTest
    public void testFieldsDisabled() {
        activity.dosage = null;
        activity.updateFields();
        assertFalse( notifications.isEnabled() );
        assertFalse( calendarEvents.isEnabled() );
        assertFalse( editTimes.isEnabled() );
        assertFalse( apply.isEnabled() );
    }

    @UiThreadTest
    public void testFieldsWithValidDosage() {
        activity.dosage = dosage;
        activity.updateFields();
        // Title contains the dosage frequency
        assertTrue( title.getText().toString().indexOf( frequency.toString() ) != -1 );
        // Title contains the drug name
        assertTrue( title.getText().toString().indexOf( drug.getName() ) != -1 );
    }

    @UiThreadTest
    public void testCalendarEvents() {
        activity.dosage = dosage;
        long millis = Calendar.getInstance().getTimeInMillis();
        frequency.addTime( millis );
        calReminder.setupReminder( activity );

        String[] projection = { Events.DTSTART, Events.DTEND, Events.TITLE, Events.RRULE, Events.DESCRIPTION,
                Events.ACCESS_LEVEL, Events.AVAILABILITY };

        ContentResolver cr = activity.getContentResolver();

        for ( Long eventID : calReminder.eventIds ) {
            String selection = Events._ID + " = " + eventID.toString();
            Cursor cur = cr.query( Events.CONTENT_URI, projection, selection, null, null );
            assertEquals( 1, cur.getCount() );
            while ( cur.moveToNext() ) {
                assertEquals( millis, cur.getLong( 0 ) );
                assertEquals( millis, cur.getLong( 1 ) );
                assertEquals( drug.getName(), cur.getString( 2 ) );
                assertEquals( "FREQ=DAILY", cur.getString( 3 ) );
                assertEquals( "One pill", cur.getString( 4 ) );
                assertEquals( Events.ACCESS_PRIVATE, cur.getInt( 5 ) );
                assertEquals( Events.AVAILABILITY_FREE, cur.getInt( 6 ) );
            }
        }
        calReminder.cancelReminder( activity );
    }
}
