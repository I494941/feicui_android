package com.feicuiedu.android.minidaily;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chenyan on 2016/11/3.
 */

public class DabatabaseHelper extends SQLiteOpenHelper {


    public DabatabaseHelper(Context context) {
        super(context, "daily.db", null, 1);
    }

    private DabatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuilder sbSql = new StringBuilder();
        sbSql.append("	CREATE TABLE daily_ (			");
        sbSql.append("	d_id  int NOT NULL,             ");
        sbSql.append("	d_name  varchar(20),            ");
        sbSql.append("	d_content  varchar(200),        ");
        sbSql.append("	d_create_time  varchar(50)      ");
        sbSql.append("	)                               ");

        db.execSQL(sbSql.toString());
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("onUpgrade");
    }
}
