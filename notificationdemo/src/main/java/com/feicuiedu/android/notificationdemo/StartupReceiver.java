package com.feicuiedu.android.notificationdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartupReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
			Intent ootStartIntent = new Intent(context, NotificationActivity.class);

		/*	ootStartIntent.setAction("android.intent.action.MAIN");
			ootStartIntent.addCategory("android.intent.category.LAUNCHER");*/
			ootStartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(ootStartIntent);
		}
		else {
			Intent ootStartIntent = new Intent(context, ContentActivity.class);

			ootStartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(ootStartIntent);
		}
	}
}
