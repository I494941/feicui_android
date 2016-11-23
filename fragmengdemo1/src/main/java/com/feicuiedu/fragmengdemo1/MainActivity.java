package com.feicuiedu.fragmengdemo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnMain;
    private Button btnFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMain = (Button) findViewById(R.id.btn_main);

        BtnListener bl = new BtnListener(this,"男神");
        btnMain.setOnClickListener(bl);
        //btnMain.performClick();




    }


}
