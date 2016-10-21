package com.feicuiedu.logindemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    // 用户名
    private TextView tvUserName;

    // 密码
    private TextView tvPassword;

    private TextView tvResult;

    // 注册
    private Button btnSignIn;

    // 登录
    private Button btnSignUp;


    Map<String, String> paramMap = new HashMap<String, String>();

    private View.OnClickListener ocl = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            String strUserName = tvUserName.getText() + "";
            String strPassword = tvPassword.getText() + "";


            paramMap.put("loginName", strUserName);
            paramMap.put("password", strPassword);

            new MyAsycTask().execute(paramMap,"/login");

        }
    };

    // 从流中读取数据
    private static byte[] read(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvUserName = (TextView) findViewById(R.id.ed_user_name);
        tvPassword = (TextView) findViewById(R.id.ed_password);
        tvResult = (TextView) findViewById(R.id.tvResult);

        btnSignIn = (Button) findViewById(R.id.btn_sign_in);
        btnSignUp = (Button) findViewById(R.id.btn_sign_up);

        btnSignUp.setOnClickListener(ocl);
    }


    private class MyAsycTask extends AsyncTask<Object,Void,String>{

        // 子线程中执行
        @Override
        protected String doInBackground(Object... params) {
            Map<String, String> urlParamMap = (Map<String, String>)params[0];
            String iName = (String) params[1];

            StringBuilder sbParam = new StringBuilder();
            for (Map.Entry<String, String> entry : urlParamMap.entrySet()) {
                sbParam.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }

            sbParam.deleteCharAt(sbParam.length() - 1);

            URL strUrl = null;

            String rtnStr = null;
            try {

                strUrl = new URL(IGobal.SERVER_URL+iName);
                HttpURLConnection httpURLConnection = (HttpURLConnection) strUrl.openConnection();
                httpURLConnection.setConnectTimeout(5000);

                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                httpURLConnection.setRequestMethod("POST");

                // 获取输出流
                OutputStream out = httpURLConnection.getOutputStream();
                out.write(sbParam.toString().getBytes());
                out.flush();

                if (httpURLConnection.getResponseCode() == 200) {

                    InputStream is = httpURLConnection.getInputStream();

                    byte[] data = read(is);
                    rtnStr = new String(data, "UTF-8");
                } else {

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return rtnStr;
        }

        // 主线线程中执行的方法
        @Override
        protected void onPostExecute(String rtnStr) {
            super.onPostExecute(rtnStr);

            JSONObject jsonObject= null;
            try {
                jsonObject = new JSONObject(rtnStr);
                String message = jsonObject.getString("message");
                String name = jsonObject.getJSONObject("data").getString("name");

                Log.d(TAG, "message= "+ message);
                Log.d(TAG, "name= "+ name);

                tvResult.setText(name+message+"!!");
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

}

