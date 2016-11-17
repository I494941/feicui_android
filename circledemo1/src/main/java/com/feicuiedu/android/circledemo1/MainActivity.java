package com.feicuiedu.android.circledemo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_homescore);
        TextView tv_score = (TextView) findViewById(R.id.tv_score);
        ClearArcView cav = (ClearArcView) findViewById(R.id.homeclear_arc);
        cav.setAngleWithAnim(100);
        tv_score.setText("100");
    }
}
