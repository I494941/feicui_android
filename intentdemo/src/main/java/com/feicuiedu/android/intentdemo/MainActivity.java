package com.feicuiedu.android.intentdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by chenyan on 2016/10/20.
 */

public class MainActivity extends AppCompatActivity {

    private Button btnCal;
    private TextView txtCalResultValue;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        txtCalResultValue = (TextView) findViewById(R.id.txtCalResultValue);

        btnCal = (Button) findViewById(R.id.btnCal);
        btnCal.setOnClickListener(ocl);
    }

    private View.OnClickListener ocl = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,InputActivity.class);
            startActivityForResult(intent, 99);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int param1 = data.getIntExtra("param1",-1);
        int param2 = data.getIntExtra("param2",-1);

        int result = param1 + param2;
        txtCalResultValue.setText(String.valueOf(result));
    }
}
