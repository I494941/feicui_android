package com.feicuiedu.android.broadcastreceiverdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnSendBroadCast;
    private Button btnSendOrderBroadCast;

    private ReceiverB receiverB;

    private final static String RECEIVER_ACTION = "my.receiver";

    private View.OnClickListener ocl = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_send_broadcast:

                    Intent broadcast = new Intent(RECEIVER_ACTION);
                    broadcast.putExtra("key","我是老大");
                    sendBroadcast(broadcast);

                    break;
                case R.id.btn_send_order_broadcast:
                    Intent sortBroadcast = new Intent("my.sort.receiver");
                    sortBroadcast.putExtra("next","你办事我放心");
                    sendOrderedBroadcast(sortBroadcast,null);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSendBroadCast = (Button) findViewById(R.id.btn_send_broadcast);
        btnSendOrderBroadCast = (Button) findViewById(R.id.btn_send_order_broadcast);

        btnSendBroadCast.setOnClickListener(ocl);
        btnSendOrderBroadCast.setOnClickListener(ocl);
    }

    @Override
    protected void onResume() {
        super.onResume();

        /*IntentFilter filter = new IntentFilter();
        filter.addAction(RECEIVER_ACTION);
        receiverB = new ReceiverB();
        registerReceiver(receiverB,filter);*/
    }

    @Override
    protected void onPause() {
        super.onPause();

        //unregisterReceiver(receiverB);
    }
}
