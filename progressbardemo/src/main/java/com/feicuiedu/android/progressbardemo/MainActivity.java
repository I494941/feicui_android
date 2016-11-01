package com.feicuiedu.android.progressbardemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    private ProgressBar pb;
    private Button btnStart;
    private Button btnEnd;
    private TextView tv_progress;

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            int i = msg.arg1;
            pb.setProgress(i);
            tv_progress.setText(i+"%");
        }
    };
    private View.OnClickListener ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_begin:


                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            int i = pb.getProgress();

                            Message message = new Message();
                            message.arg1 = i + 1;

                            if (i < 100) {
                                handler.sendMessage(message);
                            }


                            // i ++;

                        }
                    },0,100);
                    break;
                case R.id.btn_reset:
                    pb.setProgress(0);
                    pb.setSecondaryProgress(0);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv_progress = (TextView) findViewById(R.id.tv_progress);
        pb = (ProgressBar) findViewById(R.id.pb8);

        pb.setMax(100);
        btnStart = (Button) findViewById(R.id.btn_begin);
        btnEnd = (Button) findViewById(R.id.btn_reset);

        btnStart.setOnClickListener(ocl);
        btnEnd.setOnClickListener(ocl);
    }
}
