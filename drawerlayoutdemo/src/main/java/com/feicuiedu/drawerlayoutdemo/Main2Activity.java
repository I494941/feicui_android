package com.feicuiedu.drawerlayoutdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Main2Activity extends Activity {

    List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        list = new ArrayList<String>();
        list.add("haha1");
        list.add("haha2");
        list.add("haha3");
        list.add("haha4");
        list.add("haha5");

        ListView lv = (ListView) findViewById(R.id.lv_list);
        lv.setAdapter(ba);
    }

    BaseAdapter ba = new BaseAdapter() {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView tv = new TextView(Main2Activity.this);
            tv.setText(list.get(position));
            return tv;
        }
    };
}
