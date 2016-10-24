package com.feicuiedu.android.sqlitedemo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyan on 2016/10/24.
 */

public class DetailActivity extends AppCompatActivity {

    private ListView listClass;

    private List<TableInfo> listData = null;
    private ViewAdapter va = new ViewAdapter<TableInfo>() {

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater lif = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = lif.inflate(R.layout.detail_item_layout, null);
            TextView tvName = (TextView) view.findViewById(R.id.tv_detail_name);
            TextView tvNumber = (TextView) view.findViewById(R.id.tv_detail_number);
            tvName.setText(getItem(position).getName());
            tvNumber.setText(getItem(position).getNumber());
            return view;
        }
    };
    private AdapterView.OnItemClickListener ocl = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String number = listData.get(position).getNumber();
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+ number));
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Long idx = getIntent().getLongExtra("idx",-1);

        // 获取对象
        listClass = (ListView) findViewById(R.id.list_class);

        // 获取显示数据 ？？？
        listData = getData(idx);

        // 绑定适配器
        va.setListData(listData);
        listClass.setAdapter(va);

        // 设置监听
        listClass.setOnItemClickListener(ocl);
    }

    private List<TableInfo> getData(Long idx) {

        SQLiteDabseHandle handle = new SQLiteDabseHandle(this);
        SQLiteDatabase database = handle.getSQLite();

        StringBuilder sbSql = new StringBuilder();
        sbSql.append("  select        ");
        sbSql.append("      _id,number,name  ");
        sbSql.append("  from          ");
        sbSql.append("      table"+idx);

        Cursor cursor = database.rawQuery(sbSql.toString(), null);

        List<TableInfo> result = new ArrayList<TableInfo>();
        while (cursor.moveToNext()) {
            Long _id = cursor.getLong(0);
            String number = cursor.getString(1);
            String name = cursor.getString(2);

            TableInfo tableInfo = new TableInfo(_id, number,name);
            result.add(tableInfo);
        }

        return result;
    }
}
