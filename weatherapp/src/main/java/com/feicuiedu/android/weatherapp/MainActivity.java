package com.feicuiedu.android.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @ViewInject(R.id.iv_test)
    private ImageView ivTest;

    /*private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 1) {

                try {
                    JSONObject jsonObj = new JSONObject((String) msg.obj);
                    JSONArray jsonArray = jsonObj.getJSONArray("results");
                    JSONObject resutObj= jsonArray.getJSONObject(0);
                    JSONArray weatherArray = resutObj.getJSONArray("weather_data");
                    JSONObject weatherObj = weatherArray.getJSONObject(0);
                    final String dayPictureUrl = weatherObj.getString("dayPictureUrl");

                    Log.d(TAG, "dayPictureUrl: "+dayPictureUrl);

                    new Thread(){
                        @Override
                        public void run() {
                            String result = null;
                            URL url = null;
                            try {
                                url = new URL(dayPictureUrl);
                                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                                httpURLConnection.setConnectTimeout(5000);

                                httpURLConnection.setDoOutput(true);
                                httpURLConnection.setDoInput(true);

                                httpURLConnection.setRequestMethod("GET");


                                if (httpURLConnection.getResponseCode() == 200) {

                                    InputStream is = httpURLConnection.getInputStream();

                                    final Bitmap bitmap = BitmapFactory.decodeStream(is);

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Log.d(TAG, "run: >>>>>>>>>>>>>>>begin");
                                            ivTest.setImageBitmap(bitmap);
                                            Log.d(TAG, "run: >>>>>>>>>>>>>>>end");
                                        }
                                    });

                                }
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (ProtocolException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        // ivTest = (ImageView) findViewById(R.id.iv_test);

        /*final String strUrl = "http://api.map.baidu.com/images/weather/day/duoyun.png";


        new Thread(){
            @Override
            public void run() {
                String result = null;
                URL url = null;
                try {
                    url = new URL(strUrl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setConnectTimeout(5000);

                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    httpURLConnection.setRequestMethod("GET");


                    if (httpURLConnection.getResponseCode() == 200) {

                        InputStream is = httpURLConnection.getInputStream();

                        final Bitmap bitmap = BitmapFactory.decodeStream(is);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ivTest.setImageBitmap(bitmap);
                            }
                        });

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();*/

        requestNet();
    }

    private void requestNet() {


        RequestParams requestParams = new RequestParams(Gobla.SERVER_URL);
        requestParams.addParameter("location",Gobla.LOCATION);
        requestParams.addParameter("output",Gobla.OUTPUT);
        requestParams.addParameter("ak",Gobla.AK);

        Callback.Cancelable callable = x.http().get(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result){
                Log.d(TAG, "onSuccess: result="+result);
                JSONObject jsonObj = null;
                try {
                    jsonObj = new JSONObject(result);
                    JSONArray jsonArray = jsonObj.getJSONArray("results");
                    JSONObject resutObj= jsonArray.getJSONObject(0);
                    JSONArray weatherArray = resutObj.getJSONArray("weather_data");
                    JSONObject weatherObj = weatherArray.getJSONObject(0);
                    final String dayPictureUrl = weatherObj.getString("dayPictureUrl");
                    Log.d(TAG, "dayPictureUrl: "+dayPictureUrl);

                    x.image().bind(ivTest,dayPictureUrl);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d(TAG, "onError: result="+ex.getMessage());

            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.d(TAG, "onCancelled: result="+cex.getMessage());
            }

            @Override
            public void onFinished() {
                Log.d(TAG, "onFinished: ");
            }
        });

        /*new Thread() {

            @Override
            public void run() {


                String strUrl = Gobla.SERVER_URL;
                String strParams = "location=" + Gobla.LOCATION + "&output=" + Gobla.OUTPUT + "&ak=" + Gobla.AK;

                strUrl += "?"+strParams;

                String result = null;
                URL url = null;
                try {
                    url = new URL(strUrl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setConnectTimeout(5000);

                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    httpURLConnection.setRequestMethod("GET");
                    // httpURLConnection.setRequestMethod("POST");

                    // 获取输出流
                    *//*OutputStream out = httpURLConnection.getOutputStream();
                    out.write(strParams.getBytes());
                    out.flush();*//*

                    if (httpURLConnection.getResponseCode() == 200) {

                        InputStream is = httpURLConnection.getInputStream();

                        byte[] data = read(is);
                        result = new String(data, "UTF-8");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Message message = new Message();
                message.obj = result;
                message.what = 1;

                handler.sendMessage(message);
            }
        }.start();*/

    }

    // 从流中读取数据
    public byte[] read(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

}
