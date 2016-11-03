package com.feicuiedu.android.minidaily;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenyan on 2016/11/3.
 */

public class DailyMaintainActivity extends AppCompatActivity {

    private TextView tvDailyName;
    private TextView tvDailyTime;
    private EditText etDailyContent;

    private Button btnSave;
    private Button btnReturn;

    private Context context;

    private Intent intent;

    private DabatabaseHelper dh;
    private SQLiteDatabase dabase;

    private View.OnClickListener ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_save:
                    String dailyName = (String) tvDailyName.getText();
                    String dailyTime = (String) tvDailyTime.getText();
                    String dailyContent = String.valueOf(etDailyContent.getText());

                    String dailyId = String.valueOf(System.currentTimeMillis());
                    String strSql = "insert into daily_(d_id, d_name,d_create_time,d_content) values(?,?,?,?)";
                    dh = new DabatabaseHelper(context);
                    dabase = dh.getWritableDatabase();
                    dabase.execSQL(strSql,new String[]{dailyId,dailyName,dailyTime,dailyContent});
                    Toast.makeText(context,"保存成功!",Toast.LENGTH_SHORT).show();

                    break;
                case R.id.btn_return:

                    Intent intent1 = new Intent();
                    intent1.setClass(context,DailyListActivity.class);
                    startActivity(intent1);
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_layout);

        context = this;
        intent = getIntent();
        tvDailyName = (TextView) findViewById(R.id.tv_daily_name);
        tvDailyTime = (TextView) findViewById(R.id.tv_daily_time);
        tvDailyTime.setVisibility(View.VISIBLE);

        etDailyContent = (EditText) findViewById(R.id.et_daily_content);

        btnSave = (Button) findViewById(R.id.btn_save);
        btnReturn = (Button) findViewById(R.id.btn_return);

        String time = null;
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        time = sdf.format(calendar.getTime());

        sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(calendar.getTime());

        if (intent != null && intent.getStringExtra("d_id") != null) {

            String d_id = intent.getStringExtra("d_id");

            String sql = "select d_id,d_name,d_create_time,d_content from daily_ where d_id = ?";

            dh = new DabatabaseHelper(context);
            dabase = dh.getReadableDatabase();
            Cursor cursor = dabase.rawQuery(sql, new String[]{d_id});

            Map<String ,String > map = new HashMap<String,String>();

            while (cursor.moveToNext()) {
                String strId = cursor.getString(0);
                String strName = cursor.getString(1);
                String strTime = cursor.getString(2);
                String strContent = cursor.getString(3);
                map.put("d_id", strId);
                map.put("d_name", strName);
                map.put("d_time", strTime);
                map.put("d_content", strContent);
            }

            tvDailyName.setText(map.get("d_name"));
            tvDailyTime.setText(map.get("d_time"));
            etDailyContent.setText(map.get("d_content"));
        }
        else {

            tvDailyName.setText(currentDate + " 日报");
            tvDailyTime.setText(time);
        }

        btnSave.setOnClickListener(ocl);
        btnReturn.setOnClickListener(ocl);
    }
}
