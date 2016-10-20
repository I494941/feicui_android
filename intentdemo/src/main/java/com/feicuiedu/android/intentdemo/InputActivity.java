package com.feicuiedu.android.intentdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by chenyan on 2016/10/20.
 */

public class InputActivity extends AppCompatActivity {

    private EditText param1;
    private EditText param2;
    private Button btnClose;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_layout);

        param1 = (EditText) findViewById(R.id.edit_param1);
        param2 = (EditText) findViewById(R.id.edit_param2);
        btnClose = (Button) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(ocl);
    }

    private View.OnClickListener ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String strParam1 = String.valueOf(param1.getText());
            String strParam2 = String.valueOf(param2.getText());

            Intent intent = new Intent();
            intent.putExtra("param1",Integer.valueOf(strParam1));
            intent.putExtra("param2",Integer.valueOf(strParam2));

            setResult(100,intent);

            InputActivity.this.finish();
        }
    };
}
