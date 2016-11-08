package com.feicuiedu.android.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by chenyan on 2016/11/8.
 */

public class SortReceiverB extends BroadcastReceiver {
    private static final String TAG = "SortReceiverB";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive() called with: context = [" + context + "], intent = [" + intent + "]");

        String value = intent.getStringExtra("next");

        value += ",有事多和江青通知商量";


        Bundle bundle = new Bundle();
        bundle.putString("next", value);
        setResultExtras(bundle);

    }
}
