package com.dosage;

import org.codehaus.jackson.annotate.JsonTypeInfo;

import android.content.Context;

import com.utilities.Frequency;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class Reminder {
	protected String title;
	protected String detail;
	protected Frequency frequency;
	protected boolean isSetup;

	/**
	 * Construct a reminder
	 * 
	 * @param title
	 *            - Primary text of the reminder
	 * @param detail
	 *            - Secondary text of the reminder
	 */
	public Reminder(String title, String detail) {
		this.frequency = null;
		this.title = title;
		this.detail = detail;
		this.isSetup = false;
	}

	/**
	 * Setup the reminder
	 * 
	 * @param context
	 *            - Application context
	 */
	public abstract void setupReminder(Context context);

	/**
	 * Cancel the reminder
	 * 
	 * @param context
	 *            - Application context
	 */
	public abstract void cancelReminder(Context context);

	/* ----------- Functions only for Jackson serialization ------------- */

	public Reminder() {
	}

	public boolean isSetup() {
		return isSetup;
	}

	public void setSetup(boolean isSetup) {
		this.isSetup = isSetup;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof Reminder)) {
			return false;
		}
		Reminder otherReminder = (Reminder) other;
		if (title != null && !title.equals(otherReminder.title)) {
			return false;
		}
		if (title == null && title != otherReminder.title) {
			return false;
		}
		if (detail != null && !detail.equals(otherReminder.detail)) {
			return false;
		}
		if (detail == null && detail != otherReminder.detail) {
			return false;
		}
		if (frequency != null && !frequency.equals(otherReminder.frequency)) {
			return false;
		}
		if (frequency == null && frequency != otherReminder.frequency) {
			return false;
		}
		return isSetup == otherReminder.isSetup;
	}
}
