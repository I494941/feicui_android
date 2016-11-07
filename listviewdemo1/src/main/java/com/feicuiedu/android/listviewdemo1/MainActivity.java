package com.feicuiedu.android.listviewdemo1;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ListView lv1;

    private LayoutInflater lif;

    private List<String> list;


    private BaseAdapter ba = new BaseAdapter() {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public String getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Log.d(TAG, "getView() called with: position = [" + position + "], convertView = [" + convertView + "]");

            ViewHolder vh = null;

            if (convertView == null) {

                convertView = lif.inflate(R.layout.activity_main_item,null);
                vh = new ViewHolder();
                vh.tv = (TextView) convertView.findViewById(R.id.tv1);
                convertView.setTag(vh);
            }
            else {
                vh = (ViewHolder) convertView.getTag();
            }

            TextView tv = vh.tv;
            tv.setText(getItem(position));

            if (position%2 == 0) {
                tv.setBackgroundColor(Color.GREEN);
            }
            else {

                tv.setBackgroundColor(Color.RED);
            }


            return convertView;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv1 = (ListView) findViewById(R.id.lv1);
        lif = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list = getData();
        lv1.setAdapter(ba);

    }

    private List<String> getData() {

        List<String> result = new ArrayList<String>();
        for (int i = 0; i < 200; i++) {
            result.add("这是第"+i+"笔资料");
        }

        return result;
    }

    private static class ViewHolder {
        TextView tv;
    }
}
