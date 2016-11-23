package com.feicuiedu.fragmengdemo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by chenyan on 2016/11/23.
 */

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);

        /*
        动态加载碎片
        LinearLayout ll1 = (LinearLayout) findViewById(R.id.ll_1);

        TestFragment tf = new TestFragment();

        FragmentManager fm = this.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.ll_1,tf);
        ft.commit();*/
    }
}
