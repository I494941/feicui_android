package com.feicuiedu.android.storagedemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenyan on 2016/11/3.
 */

public class SQLiteActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etAge;

    private Button btnInsert;
    private Button btnQuery;

    private ListView lvUser;

    private Context context;

    private List<Map<String,String>> list = new ArrayList<Map<String,String>>();

    private View.OnClickListener ocl = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            String name = String.valueOf(etName.getText());
            String age = String.valueOf(etAge.getText());

            StringBuilder sbSql = null;
            DabatabaseHelper dh = new DabatabaseHelper(context);
            SQLiteDatabase sql = null;
            switch (v.getId()) {
                case R.id.btn_insert:

                    sbSql = new StringBuilder();
                    long id = System.currentTimeMillis();
                    sbSql.append(" insert into user_ (id, name, age) values ('"+id+"','"+name+"','"+age+"')");



                     sql = dh.getWritableDatabase();
                    sql.execSQL(sbSql.toString());

                    Toast.makeText(context,"保存用户成功",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_query:
                    sbSql = new StringBuilder();
                    sbSql.append(" select id,name,age from user_ ");

                    sql = dh.getReadableDatabase();
                    Cursor cursor = sql.rawQuery(sbSql.toString(),null);


                    while (cursor.moveToNext()) {
                        String strId = cursor.getString(0);
                        String strName = cursor.getString(1);
                        String strAge = cursor.getString(2);

                        Map<String ,String > map = new HashMap<String,String>();
                        map.put("id", strId);
                        map.put("name", strName);
                        map.put("age", strAge);
                        list.add(map);
                    }

                    ba.notifyDataSetChanged();

                    break;
            }
        }
    };

    private BaseAdapter ba = new BaseAdapter() {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Map<String,String> getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater lif = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = lif.inflate(R.layout.sql_layout_item,null);

            TextView tvUserId = (TextView) convertView.findViewById(R.id.tv_user_id);
            TextView tvUserName = (TextView) convertView.findViewById(R.id.tv_user_name);
            TextView tvUserAge = (TextView) convertView.findViewById(R.id.tv_user_age);

            Map<String,String> tmpMap = getItem(position);

            String strId = tmpMap.get("id");
            String strName = tmpMap.get("name");
            String strAge = tmpMap.get("age");

            tvUserId.setText(strId);
            tvUserName.setText(strName);
            tvUserAge.setText(strAge);


            return convertView;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sql_layout);

        context = this;
        etName = (EditText) findViewById(R.id.et_name);
        etAge = (EditText) findViewById(R.id.et_age);

        btnInsert = (Button) findViewById(R.id.btn_insert);
        btnQuery = (Button) findViewById(R.id.btn_query);

        lvUser = (ListView) findViewById(R.id.lv_user);

        lvUser.setAdapter(ba);
        
        btnInsert.setOnClickListener(ocl);
        btnQuery.setOnClickListener(ocl);
    }
}
