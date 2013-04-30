package com.dosage;

import java.util.List;

import android.app.IntentService;
import android.content.Intent;

import com.utilities.MedicineCabinet;
import com.utilities.Prescription;

/**
 * Service that sets up all prescription notifications. Notifications are
 * canceled when the phone turns off, so we must set them up again on reboot.
 */
public class SetupNotificationsService extends IntentService {

	public SetupNotificationsService() {
		super("SetupNotificationService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		List<Prescription> prescriptions = MedicineCabinet.getInstance()
				.getList();

		// Setup all prescription notification reminders
		for (Prescription prescription : prescriptions) {
			List<Reminder> reminders = prescription.getDosage().getReminders();
			for (Reminder reminder : reminders) {
				if (reminder instanceof NotificationReminder) {
					((NotificationReminder) reminder).setupReminder(this
							.getApplicationContext());
				}
			}
		}

	}

}
