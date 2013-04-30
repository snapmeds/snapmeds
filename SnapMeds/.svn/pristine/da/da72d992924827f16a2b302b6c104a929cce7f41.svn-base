package com.dosage;

import com.snapmeds.IntentProtocol;
import com.snapmeds.R;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

public class NotificationReminderService extends IntentService {

	public NotificationReminderService() {
		super("ReminderService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		String setId = intent.getStringExtra(IntentProtocol.DRUG_SET_ID);
		String title = intent.getStringExtra(IntentProtocol.NOTIFICATION_TITLE);
		String detail = intent
				.getStringExtra(IntentProtocol.NOTIFICATION_DETAIL);
		Integer alarmId = intent.getIntExtra(
				IntentProtocol.NOTIFICATION_ALARM_ID, 0);

		// Build notification
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				this.getApplicationContext())
				.setSmallIcon(R.drawable.ic_launcher).setContentTitle(title)
				.setContentText(detail);

		// Link notification click to a prescription detail page
		Intent resultIntent = new Intent(this,
				com.snapmeds.PrescriptionDetailActivity.class);
		resultIntent.putExtra(IntentProtocol.DRUG_SET_ID, setId);

		// Ensure backward navigation from app to home screen
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this
				.getApplicationContext());
		stackBuilder
				.addParentStack(com.snapmeds.PrescriptionDetailActivity.class);
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
				alarmId, PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);

		// Display notification
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(alarmId, mBuilder.build());
	}

}
