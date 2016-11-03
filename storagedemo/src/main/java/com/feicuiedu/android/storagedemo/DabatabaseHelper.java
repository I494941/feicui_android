package com.feicuiedu.android.storagedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chenyan on 2016/11/3.
 */

public class DabatabaseHelper extends SQLiteOpenHelper {


    public DabatabaseHelper(Context context) {
        super(context, "test.db", null, 2);
    }
    private DabatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(" create table user_ (id int, name varchar(200), age int ) ");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("onUpgrade");
    }
}
