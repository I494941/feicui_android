package com.feicuiedu.android.notificationdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NotificationActivity extends AppCompatActivity {

	private Context context= NotificationActivity.this;

	private NotificationManager notificationManager;
	private Notification.Builder builder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification_main);
		
		Log.d("MainActivity>>>>>", "开机启动了。。。。。");
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		Button btn1 = (Button) findViewById(R.id.button1);
		Button btn2 = (Button) findViewById(R.id.button2);
		Button btn3 = (Button) findViewById(R.id.button3);
		btn1.setOnClickListener(ocl);
		btn2.setOnClickListener(ocl);
		btn3.setOnClickListener(ocl);
	}
	
	private OnClickListener ocl = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Notification notify = null;
			builder = new Notification.Builder(context);

			Intent intent = new Intent(context, NotificationActivity.class);
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

			builder.setContentIntent(pendingIntent);
			switch(v.getId()) {

				case R.id.button1:
					/*notify.icon = R.drawable.icon_app;
					notify.tickerText="显示第一个通知";
					notify.when=System.currentTimeMillis();
					notify.defaults = Notification.DEFAULT_ALL;
					//notify.setLatestEventInfo(context, "标题1", "好好睡觉", null);*/


					builder.setContentTitle("新通知提示");
					builder.setContentText("你好 你好");
					builder.setSmallIcon(R.drawable.icon_folder);
					builder.setTicker("你有新的消息");// 第一次出现在状态栏
					builder.setDefaults(Notification.DEFAULT_ALL);// 默认的闪光，铃声，震动

					if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
						notify = builder.build();
					}

					notificationManager.notify(1000,notify);

					break;
				case R.id.button2:
					/*
					notify = new Notification(R.drawable.icon_cpp,"显示第二个通知",System.currentTimeMillis());
					notify.flags = Notification.FLAG_AUTO_CANCEL;
					Intent intent = new Intent(context, ContentActivity.class);
					PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
					//notify.setLatestEventInfo(context, "通知", "查看详细内容", pendingIntent);
					nm.notify(1002, notify);*/


					intent = new Intent(context, MainActivity.class);
					pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

					builder.setContentIntent(pendingIntent);

					builder.setContentTitle("新通知提示1111");
					builder.setContentText("你好 你好111");
					builder.setSmallIcon(R.mipmap.ic_launcher);
					builder.setTicker("你有新的消息1111");// 第一次出现在状态栏
					builder.setDefaults(Notification.DEFAULT_ALL);// 默认的闪光，铃声，震动

					if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
						notify = builder.build();


						notify.flags = Notification.FLAG_AUTO_CANCEL;
					}

					notificationManager.notify(1001,notify);


					break;
				case R.id.button3:

					Intent intent1 = new Intent(context, ContentActivity.class);
					startActivity(intent1);
					finish();
					break;
			}
			
		}
	};

}
