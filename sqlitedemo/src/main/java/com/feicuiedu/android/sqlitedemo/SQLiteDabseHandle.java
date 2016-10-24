package com.feicuiedu.android.sqlitedemo;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by chenyan on 2016/10/24.
 */

public class SQLiteDabseHandle {

    private Context context;

    public SQLiteDabseHandle(Context context) {
        this.context = context;
    }


    // 把assets/db下的commonnum.db文件复制到/data/data/packagename/db目录下

    // 文件复制
    /*
            1.找到源文件名称，源路径  目标文件名称，目标路径
     */

    private File copyDabaseFile() {

        String res_file_name = "commonnum.db";
        String res_file_path = "db";

        String target_file_name = "num.db";
        String target_file_path = "/data/data/" + context.getPackageName() + "/db";

        File file_target_path = null;
        File file_target_file = null;

        //  读取源路径下，源文件，使用源文件构件字节输入流 InputStream
        //  在目标路径创建目标文件，使用目标文件构件字节输出流 OutputStream
        //  从输入流中读取字节文件写入到输出流中

        try {

            // 判断目标路径是否存在，如果不存在则创建
            file_target_path = new File(target_file_path);

            if (!file_target_path.exists()) {
                file_target_path.mkdirs();
            }

            // 判断目标文件是否存在，如果不存在则创建
            file_target_file = new File(file_target_path, target_file_name);
            if (!file_target_file.exists()) {
                file_target_file.createNewFile();
            }

            AssetManager assetManager = context.getAssets();

            InputStream is = assetManager.open(res_file_path + File.separator + res_file_name);
            OutputStream os = new FileOutputStream(new File(target_file_path, target_file_name));

            int tmp = 0;

            while ((tmp = is.read()) != -1) {
                os.write(tmp);
            }

            os.flush();
            os.close();
            is.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file_target_file;
    }


    public SQLiteDatabase getSQLite() {
        SQLiteDatabase database = null;
        File dbFile = copyDabaseFile();

        if (dbFile != null && dbFile.exists()) {
            database = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
        }

        return database;
    }
}
