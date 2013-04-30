package com.dosage;

import java.util.Calendar;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.snapmeds.AndroidStorage;
import com.snapmeds.IntentProtocol;
import com.snapmeds.MainActivity;
import com.snapmeds.R;
import com.utilities.Dosage;
import com.utilities.Frequency;
import com.utilities.MedicineCabinet;
import com.utilities.Prescription;

public class RemindersActivity extends Activity {
	public Prescription prescription;
	public Dosage dosage;
	private CalendarReminder calReminder;
	private NotificationReminder notifyReminder;
	private boolean timesSetup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		setContentView(R.layout.activity_reminders);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		Intent intent = getIntent();
		String setId = intent.getStringExtra(IntentProtocol.DRUG_SET_ID);
		try {
			prescription = MedicineCabinet.getInstance().get(setId);
			if (prescription != null) {
				dosage = prescription.getDosage();
				updateFields();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		AndroidStorage.setContext(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dosage_parser, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
	}

	public void updateFields() {
		if (dosage == null) {
			((TextView) findViewById(R.id.dosage_info))
					.setText("Dosage Information is required to set up reminders.");
			((CheckBox) findViewById(R.id.notifications)).setEnabled(false);
			((CheckBox) findViewById(R.id.calendar_events)).setEnabled(false);
			((Button) findViewById(R.id.editTimesButton)).setEnabled(false);
			((Button) findViewById(R.id.applyButton)).setEnabled(false);
		} else {
			String titleText = prescription.getDrug().getName() + ": "
					+ dosage.getFrequency().toString();
			((TextView) findViewById(R.id.dosage_info)).setText(titleText);
			for (Reminder reminder : dosage.getReminders()) {
				if (reminder instanceof NotificationReminder) {
					notifyReminder = (NotificationReminder) reminder;
					((CheckBox) findViewById(R.id.notifications))
							.setChecked(reminder.isSetup);
				} else if (reminder instanceof CalendarReminder) {
					calReminder = (CalendarReminder) reminder;
					((CheckBox) findViewById(R.id.calendar_events))
							.setChecked(reminder.isSetup);
				}
			}
			this.timesSetup = dosage.getFrequency().getTimes().size() > 0;
		}
	}

	public void onCheckBoxClicked(View view) {
		if (!timesSetup) {
			// Open timepickers to set the times value in the dosage frequency
			TimePickerListener listener = new TimePickerListener(
					dosage.getFrequency(), null);
			TimePickerFragment timePicker = new TimePickerFragment();

			// Bind listener to fragment
			timePicker.setOnTimeSetListener(listener);
			timePicker.show(getFragmentManager(), "timePicker");
		}
	}

	public void onEditTimes(View view) {
		dosage.cancelReminders(getApplicationContext());

		dosage.getFrequency().getTimes().clear();
		// Open timepickers to set the times value in the dosage frequency
		TimePickerListener listener = new TimePickerListener(
				dosage.getFrequency(), null);
		TimePickerFragment timePicker = new TimePickerFragment();

		// Bind listener to fragment
		timePicker.setOnTimeSetListener(listener);
		timePicker.show(getFragmentManager(), "timePicker");
	}

	public void onApply(View view) {
		if (((CheckBox) findViewById(R.id.notifications)).isChecked()) {
			notifyReminder.setupReminder(getApplicationContext());
		} else {
			notifyReminder.cancelReminder(getApplicationContext());
		}
		if (((CheckBox) findViewById(R.id.calendar_events)).isChecked()) {
			calReminder.setupReminder(getApplicationContext());
		} else {
			calReminder.cancelReminder(getApplicationContext());
		}
		prescription.save();
		Toast.makeText(getApplicationContext(), R.string.reminders_saved,
				Toast.LENGTH_SHORT).show();
	}

	/**
	 * Class responsible for opening one or several timepickers and retrieving
	 * the values set by the user.
	 */
	public class TimePickerListener implements
			TimePickerDialog.OnTimeSetListener {
		private Frequency frequency;
		private boolean isTimeSet;
		private int popupsRemaining;

		/**
		 * Construct a timepicker listener
		 * 
		 * @param frequency
		 *            - Frequency to add user selected times to.
		 * @param reserved
		 *            - Used internally; caller should pass in null.
		 */
		public TimePickerListener(Frequency frequency, Integer reserved) {
			this.frequency = frequency;
			isTimeSet = false;

			if (reserved == null) {
				// Get number of subsequent timepickers from the number of user
				// selected times the frequency needs to receive
				this.popupsRemaining = frequency.getNumTimes() - 1;
			} else {
				this.popupsRemaining = reserved;
			}
		}

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// There is an android bug where onTimeSet is called twice per time
			// set. So we have use a flag to ensure we only respond once
			if (isTimeSet)
				return;
			isTimeSet = true;

			long milliseconds = 0;
			Calendar selectedTime = Calendar.getInstance();
			selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
			selectedTime.set(Calendar.MINUTE, minute);
			milliseconds = selectedTime.getTimeInMillis();
			frequency.addTime(milliseconds);

			if (popupsRemaining > 0) {
				// If more timepickers should be opened, open one now
				TimePickerListener listener = new TimePickerListener(frequency,
						popupsRemaining - 1);
				TimePickerFragment timePicker = new TimePickerFragment();
				timePicker.setOnTimeSetListener(listener);
				timePicker.show(getFragmentManager(), "timePicker");
			} else {
				// We were the final timepicker, so we provided the frequency
				// object all of its necessary times, so we are ready to set the
				// reminders
				calReminder.setFrequency(frequency);
				notifyReminder.setFrequency(frequency);
				timesSetup = true;
				updateFields();
			}
		}

	}

	// enables the home button
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This is called when the Home (Up) button is pressed
			// in the Action Bar.
			Intent parentActivityIntent = new Intent(this, MainActivity.class);
			parentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(parentActivityIntent);
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
