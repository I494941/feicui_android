package com.feicuiedu.android.minidaily;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenyan on 2016/11/3.
 */

public class DailyListActivity extends AppCompatActivity {

    private Button btnCreate;
    private ListView lvDailyList;

    private Context context;

    private View.OnClickListener ocl = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            Intent intent = new Intent();
            intent.setClass(context,DailyMaintainActivity.class);
            startActivity(intent);
            finish();
        }
    };

    private List<Map<String, String>> list = null;

    private DataAdapter da = new DataAdapter<Map<String,String>>() {

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater lif = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = lif.inflate(R.layout.list_item_layout,null);

            TextView tvDailyId = (TextView) convertView.findViewById(R.id.tv_item_id);
            TextView tvDailyName = (TextView) convertView.findViewById(R.id.tv_item_name);
            TextView tvDailyTime = (TextView) convertView.findViewById(R.id.tv_item_time);

            Map<String,String> tmpMap = getItem(position);

            String strId = tmpMap.get("d_id");
            String strName = tmpMap.get("d_name");
            String strTime = tmpMap.get("d_time");

            tvDailyId.setText(strId);
            tvDailyName.setText(strName);
            tvDailyTime.setText(strTime);


            return convertView;
        }
    };

    private AdapterView.OnItemClickListener oicl = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Map<String,String> tmp = list.get(position);

            Intent intent = new Intent();
            intent.setClass(context,DailyMaintainActivity.class);
            intent.putExtra("d_id",tmp.get("d_id"));
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        context = this;

        btnCreate = (Button) findViewById(R.id.btn_create_daily);
        lvDailyList = (ListView) findViewById(R.id.lv_daily_list);

        btnCreate.setOnClickListener(ocl);


        lvDailyList.setAdapter(da);
        lvDailyList.setOnItemClickListener(oicl);
    }

    @Override
    protected void onResume() {
        super.onResume();

        StringBuilder sbSql = null;
        DabatabaseHelper dh = new DabatabaseHelper(context);
        SQLiteDatabase sql = null;

        sbSql = new StringBuilder();
        sbSql.append(" select d_id,d_name,d_create_time from daily_ ");

        sql = dh.getReadableDatabase();
        Cursor cursor = sql.rawQuery(sbSql.toString(),null);

        list = new ArrayList<Map<String,String>>();
        while (cursor.moveToNext()) {
            String strId = cursor.getString(0);
            String strName = cursor.getString(1);
            String strTime = cursor.getString(2);

            Map<String ,String > map = new HashMap<String,String>();
            map.put("d_id", strId);
            map.put("d_name", strName);
            map.put("d_time", strTime);
            list.add(map);
        }

        da.setList(list);

        da.notifyDataSetChanged();
    }
}
