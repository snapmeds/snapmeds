package com.dosage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Receiver responsible for setting up notifications after a phone reboot
 * (Notifications are based on alarms which are cancelled whenever the phone
 * shuts off)
 */
public class BootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
			Intent serviceIntent = new Intent(context,
					SetupNotificationsService.class);
			context.startService(serviceIntent);
		}
	}

}
