package com.feicuiedu.android.circledemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

	private Button btn_auto;
	private Button btn_add;
	private MyCircle circle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn_auto = (Button) findViewById(R.id.btn_auto);
		btn_add = (Button) findViewById(R.id.btn_add);
		
		circle = (MyCircle) findViewById(R.id.circle1);
		btn_add.setOnClickListener(ock);
		btn_auto.setOnClickListener(ock);
	}

	private OnClickListener ock = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			int current_progress = circle.getProgress();
			
			switch(v.getId()) {
			case R.id.btn_add:
				circle.setProgress(current_progress+5);
				break;
			case R.id.btn_auto:
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					
					@Override
					public void run() {
						
						int temp = circle.getProgress();
						circle.setProgress(temp + 5);
						
						if (temp >= 100||temp < 0) {
							this.cancel();
						}
						
					}
				}, 0, 500);
				break;
			
			}
			if (current_progress >= 100||current_progress < 0) {
				circle.setProgress(0);
			}
			
		}
	};

}
