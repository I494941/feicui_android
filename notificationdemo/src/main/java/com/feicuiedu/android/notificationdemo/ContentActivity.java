/**
 * 
 */
package com.feicuiedu.android.notificationdemo;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author chenyan
 *
 */
public class ContentActivity extends Activity {

	private Context context = ContentActivity.this;
	private NotificationManager nm;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification_delete);

		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		Button btn1 = (Button) findViewById(R.id.del_button1);
		Button btn2 = (Button) findViewById(R.id.del_button2);
		Button btn3 = (Button) findViewById(R.id.del_button3);
		Button del_return = (Button) findViewById(R.id.del_return);
		
		btn1.setOnClickListener(ocl);
		btn2.setOnClickListener(ocl);
		btn3.setOnClickListener(ocl);
		del_return.setOnClickListener(ocl);
	}

	private OnClickListener ocl = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Notification notify = new Notification();
			switch (v.getId()) {
			case R.id.button1:
				nm.cancel(1001);
				break;
			case R.id.button2:

				nm.cancel(1002);

				break;
			case R.id.button3:
				
				nm.cancelAll();
				break;
			
			case R.id.del_return:
				Intent intent = new Intent(context, NotificationActivity.class);
				startActivity(intent);
				finish();
				break;
			}
		}
	};
}
