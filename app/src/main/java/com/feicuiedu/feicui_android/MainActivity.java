package com.feicuiedu.feicui_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
    }

    public void jump(View view) {
        Intent intent = new Intent();
        /*intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:18678294785"));*/
        intent.setAction("com.second1");
        //intent.addCategory("myCategory1");
        //intent.addCategory("myCategory2");

        /*<data
        android:scheme="xxx"
        android:host="baidu.com"
        android:port="88"
        android:pathPrefix="/sky"/>*/

       /* intent.putExtra("key","hello");
        intent.putExtra("intkey",1234567);*/


        /*Bundle bundle = new Bundle();
        bundle.putString("key","haha");
        bundle.putInt("intKey",33);

        intent.putExtras(bundle);*/

        intent.setClass(this,ThirdActivity.class);

        startActivityForResult(intent,201);
        // intent.setData(Uri.parse("xxx://baidu.com:88/sky1121212"));
        // startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult: requestCode="+requestCode);
        Log.d(TAG, "onActivityResult: resultCode="+resultCode);
        Log.d(TAG, "onActivityResult: data.key="+data.getStringExtra("key"));
    }
}
