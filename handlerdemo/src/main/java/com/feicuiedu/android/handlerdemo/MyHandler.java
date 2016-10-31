package com.feicuiedu.android.handlerdemo;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;

/**
 * Created by chenyan on 2016/10/31.
 */

public class MyHandler extends Handler {

    private static final String TAG = "MyHandler";

    private Button btn2;

    public MyHandler(Button btn2) {
        this.btn2 = btn2;
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg.what == 1) {
            Log.d(TAG, "handleMessage: what is 1");
            btn2.setBackgroundColor(Color.GREEN);
            btn2.setText((String)msg.obj);
        } else if (msg.what == 2) {
            Log.d(TAG, "handleMessage: what is 2");
        }
    }
}
