package com.feicuiedu.android.handlerdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

/**
 * Created by chenyan on 2016/10/28.
 */

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private Button btn2;

    /*private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 1) {
                btn2.setBackgroundColor(Color.RED);
                btn2.setText("皮尔.布鲁斯南");
            }
        }
    };*/


    private View.OnClickListener ocl = new View.OnClickListener() {

        @Override
        public void onClick(final View v) {

            switch (v.getId()) {
                case R.id.btn1:

                    btn1.setBackgroundColor(Color.GREEN);
                    btn1.setText("罗布特.唐尼");
                    break;
                case R.id.btn2:

                    /*MyThread thread = new MyThread();

                    // 如何把btn2传入到线程中去？
                    thread.setBtn2(btn2);

                    thread.start();*/



                    /*new Thread() {

                        @Override
                        public void run() {

                            try {

                                // 2000
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            v.post(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            btn2.setBackgroundColor(Color.GREEN);
                                            btn2.setText("皮尔.布鲁斯南");
                                        }
                                    }
                            );
                        }
                    }.start();*/


                   /*Message message = new Message();
                    message.what = 1;
                    handler.sendMessageDelayed(message,2000);*/

                   /* MyHandler myHandler = new MyHandler();
                    myHandler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            btn2.setBackgroundColor(Color.GREEN);
                            btn2.setText("皮尔.布鲁斯南");
                        }
                    },2000);
*/

                   /* MyHandler myHandler = new MyHandler((Button) v);
                    Message message = new Message();
                    message.what = 1;
                    message.obj = "嬴政";
                    myHandler.sendMessageDelayed(message,2000);*/

                    String str = "李世民";

                    new MyAsyncTask((Button) v).execute(str,str, str);

                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);

        btn1.setOnClickListener(ocl);
        btn2.setOnClickListener(ocl);

        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, 800);
        animation.setDuration(3000);
        animation.setRepeatCount(1000);
        btn1.startAnimation(animation);
    }



}
