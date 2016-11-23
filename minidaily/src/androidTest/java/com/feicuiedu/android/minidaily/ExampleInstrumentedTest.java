package com.feicuiedu.android.minidaily;

import android.app.ActivityManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.feicuiedu.android.minidaily", appContext.getPackageName());
    }

    @Test
    public  void  testList() {

        Context context = InstrumentationRegistry.getTargetContext();


        StringBuilder sbSql = null;
        DabatabaseHelper dh = new DabatabaseHelper(context);
        SQLiteDatabase sql = null;

        sbSql = new StringBuilder();
        sbSql.append(" select d_id,d_name,d_create_time from daily_ ");

        sql = dh.getReadableDatabase();
        Cursor cursor = sql.rawQuery(sbSql.toString(),null);

        ArrayList<Map<String,String>> list = new ArrayList<Map<String,String>>();
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

        Log.d(TAG, "testList: "+list);
    }

    public void testGetprogress() {
        Context context = InstrumentationRegistry.getTargetContext();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //获得MemoryInfo对象
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo() ;
        //获得系统可用内存，保存在MemoryInfo对象上
        am.getMemoryInfo(memoryInfo) ;
       /* long memSize = memoryInfo.availMem ;

        Log.d(TAG, "testGetprogress: " + );*/
    }
}
