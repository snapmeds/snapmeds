package com.utilities;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.dosage.Reminder;

public class Dosage {

	private String quantity;
	private Frequency frequency;
	private String route;
	private String warnings;
	private String instructions;
	private String reason;
	private String otherInfo;
	private boolean manuallyEntered;
	private List<Reminder> reminders;

	public Dosage() {
		clearFields();
	}

	public Dosage(Dosage otherDosage) {
		if (otherDosage != null) {
			this.quantity = otherDosage.quantity;
			this.frequency = otherDosage.frequency;
			this.route = otherDosage.route;
			this.warnings = otherDosage.warnings;
			this.instructions = otherDosage.instructions;
			this.reason = otherDosage.reason;
			this.manuallyEntered = otherDosage.manuallyEntered;
			reminders = new ArrayList<Reminder>();
		} else {
			clearFields();
		}
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		if (quantity != null) {
			this.quantity = capitalize(quantity);
		}
	}

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = capitalize(route);
	}

	public String getWarnings() {
		return warnings;
	}

	public void setWarnings(String warnings) {
		this.warnings = capitalize(warnings);
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = capitalize(instructions);
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = capitalize(reason);
	}

	public String getOtherInfo() {
		if (manuallyEntered) {
			return otherInfo;
		} else {
			StringBuffer buf = new StringBuffer();
			if (route != "")
				buf.append("; " + route);
			if (instructions != "")
				buf.append("; " + instructions);
			if (warnings != "")
				buf.append("; " + warnings);
			if (reason != "")
				buf.append("; " + reason);
			return buf.toString();
		}

	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = capitalize(otherInfo);
	}

	public boolean isManuallyEntered() {
		return manuallyEntered;
	}

	public void setManuallyEntered(boolean manuallyEntered) {
		this.manuallyEntered = manuallyEntered;
	}

	public List<Reminder> getReminders() {
		return this.reminders;
	}

	public void setReminders(List<Reminder> reminders) {
		this.reminders = reminders;
	}

	public void addReminder(Reminder reminder) {
		this.reminders.add(reminder);
	}

	public void setupReminders(Context context) {
		for (Reminder reminder : reminders) {
			reminder.setupReminder(context);
		}
	}

	public void cancelReminders(Context context) {
		for (Reminder reminder : reminders) {
			reminder.cancelReminder(context);
		}
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(quantity);
		buf.append('\n' + frequency.toString());
		if (manuallyEntered) {
			if (otherInfo != "")
				buf.append('\n' + otherInfo);
		} else {
			if (route != "")
				buf.append('\n' + route);
			if (instructions != "")
				buf.append('\n' + instructions);
			if (warnings != "")
				buf.append('\n' + warnings);
			if (reason != "")
				buf.append('\n' + reason);
		}
		return buf.toString();
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof Dosage)) {
			return false;
		}
		Dosage otherDosage = (Dosage) other;
		if (!getQuantity().equals(otherDosage.getQuantity())) {
			return false;
		}
		if (!getFrequency().equals(otherDosage.frequency)) {
			return false;
		}
		if (!route.equals(otherDosage.route)) {
			return false;
		}
		if (!warnings.equals(otherDosage.warnings)) {
			return false;
		}
		if (!instructions.equals(otherDosage.instructions)) {
			return false;
		}
		if (!reason.equals(otherDosage.reason)) {
			return false;
		}
		if (!reminders.equals(otherDosage.reminders)) {
			return false;
		}
		return true;
	}

	/**
	 * Capitalize first letter in string
	 */
	private String capitalize(String words) {
		if (!words.isEmpty()) {
			return Character.toUpperCase(words.charAt(0)) + words.substring(1);
		}
		return "";
	}

	private void clearFields() {
		quantity = "";
		frequency = new Frequency();
		route = "";
		warnings = "";
		instructions = "";
		reason = "";
		otherInfo = "";
		manuallyEntered = true;
		reminders = new ArrayList<Reminder>();
	}
}
