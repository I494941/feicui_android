package com.feicuiedu.android.circledemo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PiechartView pv1  = (PiechartView) findViewById(R.id.pv1);
        pv1.setPiechartProportionWithAnim(0.0f,1.0f);
    }
}
