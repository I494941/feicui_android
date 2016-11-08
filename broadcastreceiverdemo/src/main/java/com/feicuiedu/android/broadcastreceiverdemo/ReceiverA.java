package com.feicuiedu.android.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by chenyan on 2016/11/8.
 */

public class ReceiverA extends BroadcastReceiver {
    private static final String TAG = "ReceiverA";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive() called with: context = [" + context + "], intent = [" + intent + "]");
        String value = intent.getStringExtra("key");
        Log.d(TAG, "onReceive: value="+value);

    }
}
