package com.feicuiedu.android.notificationdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    private NotificationManager notificationManager;
    private Notification.Builder builder;
    private Button btn1;
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);



        btn1 = (Button) findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder = new Notification.Builder(MainActivity.this);
                Intent intent = new Intent(MainActivity.this, MainActivity.class);

                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
                builder.setContentIntent(pendingIntent);
                builder.setContentTitle("新通知提示222222");
                builder.setContentText("你好 你好2222");
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setTicker("你有新的消息222");// 第一次出现在状态栏
                builder.setDefaults(Notification.DEFAULT_ALL);// 默认的闪光，铃声，震动
                Notification notification = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    notification = builder.build();
                }

                notificationManager.notify(1003,notification);
            }
        });

        btn2 = (Button) findViewById(R.id.btn2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(">>>>>","111111");
                builder = new Notification.Builder(MainActivity.this);
                RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.notification_layout);
                /*Icon icon = new Icon(R.drawable.icon_doc);
                remoteViews.setImageViewIcon(R.id.img1,icon);*/
                remoteViews.setImageViewResource(R.id.img1,R.drawable.icon_cpp);
                remoteViews.setTextViewText(R.id.txt1,"标题");
                remoteViews.setTextViewText(R.id.txt2,"内容");


                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);
                builder.setContentIntent(pendingIntent);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    builder.setCustomContentView(remoteViews);
                }
                else {
                    builder.setContent(remoteViews);
                }
                builder.setDefaults(Notification.DEFAULT_ALL);
                Notification notification = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

                    notification = builder.build();
                    notification.contentView  = remoteViews;
                    notification.icon = R.drawable.icon_css;
                    //notification.

                }
                notificationManager.notify(1004,notification);
            }
        });
    }


}
