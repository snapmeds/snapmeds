package com.dosage;

import java.util.Calendar;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

public class TimePickerFragment extends DialogFragment {

	private TimePickerDialog.OnTimeSetListener callback;

	public void setOnTimeSetListener(TimePickerDialog.OnTimeSetListener callback) {
		this.callback = callback;
	}

	public Dialog onCreateDialog(Bundle savedInstanceState) {

		// Use the current time as the default values for the picker
		final Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);

		// Create a new instance of TimePickerDialog and return it
		return new TimePickerDialog(getActivity(), callback, hour, minute,
				DateFormat.is24HourFormat(getActivity()));
	}

}