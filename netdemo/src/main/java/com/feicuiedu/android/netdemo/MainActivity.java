package com.feicuiedu.android.netdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    // 注册
    private Button btnSignIn;

    // 登录
    private Button btnSignUp;
    private View.OnClickListener ocl = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            String strUserName = tvUserName.getText() + "";
            String strPassword = tvPassword.getText() + "";

            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("loginName", strUserName);
            paramMap.put("password", strPassword);

            String resultStr = getDataFromServere(paramMap);
            Log.d(TAG, "onClick: resultStr = "+ resultStr);
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
        setContentView(com.feicuiedu.android.netdemo1.R.layout.activity_main);

        tvUserName = (TextView) findViewById(com.feicuiedu.android.netdemo1.R.id.ed_user_name);
        tvPassword = (TextView) findViewById(com.feicuiedu.android.netdemo1.R.id.ed_password);

        btnSignIn = (Button) findViewById(com.feicuiedu.android.netdemo1.R.id.btn_sign_in);
        btnSignUp = (Button) findViewById(com.feicuiedu.android.netdemo1.R.id.btn_sign_up);

        btnSignUp.setOnClickListener(ocl);
    }

    private String getDataFromServere(Map<String, String> paramMap) {

        StringBuilder sbParam = new StringBuilder();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            sbParam.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }

        sbParam.deleteCharAt(sbParam.length() - 1);

        URL strUrl = null;

        String rtnStr = null;
        try {
            strUrl = new URL(IGobal.SERVER_URL);
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

}

