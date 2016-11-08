package com.feicuiedu.android.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by chenyan on 2016/11/8.
 */

public class SortReceiverA extends BroadcastReceiver {
    private static final String TAG = "SortReceiverA";
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, "onReceive() called with: context = [" + context + "], intent = [" + intent + "]");
        Bundle bundle = getResultExtras(true);
        String content = bundle.getString("next");
        Log.d(TAG, "onReceive() content=["+content+"]");
    }
}
