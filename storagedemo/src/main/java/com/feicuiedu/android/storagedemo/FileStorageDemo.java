package com.feicuiedu.android.storagedemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by chenyan on 2016/11/2.
 */

public class FileStorageDemo extends AppCompatActivity {

    private Button btnFile;
    private Button btnSD;
    private Button btnSP;

    private Button btnReadFile;
    private Button btnReadSD;
    private Button btnReadSP;

    private EditText etContent;

    private String strContent;

    private Context context;
    private View.OnClickListener ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            strContent = String.valueOf(etContent.getText());
            boolean isSDExists = false;
            switch (v.getId()) {
                case R.id.btn_file:
                    try {
                        // Context.MODE_PRIVATE 只能被创建文件的应用所访问
                        // Context.MODE_APPEND 文件内容是被追加的
                        // Context.MODE_WORLD_READABLE 能够被同手机上另外应用  读取
                        // ,Context.MODE_WORLD_WRITEABLE 能够被同手机上另外应用  修改
                        // 把myfile.txt文件 创建到 /data/data/Package Name/files 目录下 并写入内容
                        OutputStream os = context.openFileOutput("myfile.txt",Context.MODE_PRIVATE);
                        os.write(strContent.getBytes());
                        os.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context,"文件保存成功!",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_sd:


                    // android.permission.MOUNT_UNMOUNT_FILESYSTEMS
                    // android.permission.WRITE_EXTERNAL_STORAGE

                    // 判断SD卡是否插入并且可读写 在这之前需要加权限
                    isSDExists = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

                    if (isSDExists) {
                        try {

                            // 获取sd卡的绝对路径
                            String path = Environment.getExternalStorageDirectory().getCanonicalPath();
                            String fileName = "sd_file.txt";

                            File targetFile = new File(path,fileName);
                            OutputStream os = new FileOutputStream(targetFile);
                            os.write(strContent.getBytes());
                            os.flush();
                            os.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(context,"SD卡保存成功!",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(context,"没有SD卡",Toast.LENGTH_SHORT).show();
                    }

                    break;
                case R.id.btn_sp:

                    SharedPreferences sharedPreferences = context.getSharedPreferences("sp_file",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("key",strContent);

                    editor.commit();

                    Toast.makeText(context,"SharedPreferences保存成功!",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_read_file:

                    try {

                        // 读取 /data/data/packagename/files/myfile.txt文件 并把文件内容放到输入流中去
                        InputStream is = context.openFileInput("myfile.txt");

                        // 创建一个字节数组输出流
                        ByteArrayOutputStream bao = new ByteArrayOutputStream();

                        // 从输入流中读取所有字节，放到ByteArrayOutputStream对象中
                        int tmp = 0;
                        while ((tmp = is.read()) != -1) {
                            bao.write(tmp);
                        }

                        // 放到ByteArrayOutputStream对象可以把里面的字节，变成byte[],byte[]再转换成字符串(String)
                        String strRead = new String(bao.toByteArray(),"utf-8");
                        bao.close();
                        is.close();

                        Toast.makeText(context,"从本地文件读取:"+strRead,Toast.LENGTH_SHORT).show();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.btn_read_sd:

                    isSDExists = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

                    if (isSDExists) {
                        try {
                            String path = Environment.getExternalStorageDirectory().getCanonicalPath();
                            String fileName = "sd_file.txt";

                            File targetFile = new File(path,fileName);
                            InputStream is = new FileInputStream(targetFile);
                            ByteArrayOutputStream bao = new ByteArrayOutputStream();
                            int tmp = 0;
                            while ((tmp = is.read())!= -1) {
                                bao.write(tmp);
                            }
                            String strRead = new String(bao.toByteArray(),"utf-8");
                            bao.close();
                            is.close();
                            Toast.makeText(context,"从SD卡读取:"+strRead,Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        Toast.makeText(context,"没有SD卡",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btn_read_sp:

                    SharedPreferences sharedPreferences1 = context.getSharedPreferences("sp_file",Context.MODE_PRIVATE);

                    Toast.makeText(context,"从SharedPreferences读取:"+sharedPreferences1.getString("key","没有取到"),Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_layout);

        context = this;

        etContent = (EditText) findViewById(R.id.et_content);

        btnFile = (Button) findViewById(R.id.btn_file);
        btnSD = (Button) findViewById(R.id.btn_sd);
        btnSP = (Button) findViewById(R.id.btn_sp);

        btnReadFile = (Button) findViewById(R.id.btn_read_file);
        btnReadSD = (Button) findViewById(R.id.btn_read_sd);
        btnReadSP = (Button) findViewById(R.id.btn_read_sp);

        btnFile.setOnClickListener(ocl);
        btnSD.setOnClickListener(ocl);
        btnSP.setOnClickListener(ocl);

        btnReadFile.setOnClickListener(ocl);
        btnReadSD.setOnClickListener(ocl);
        btnReadSP.setOnClickListener(ocl);
    }
}
