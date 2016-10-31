package com.feicuiedu.android.handlerdemo;

import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.Button;

/**
 * Created by chenyan on 2016/10/31.
 */

public class MyAsyncTask extends AsyncTask<String,Void,String>{

    private Button btn2;

    public MyAsyncTask(Button btn2) {
        this.btn2 = btn2;
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return params[0];
    }

    @Override
    protected void onPostExecute(String s) {

        btn2.setBackgroundColor(Color.GREEN);
        btn2.setText(s);
    }
}
