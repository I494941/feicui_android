package com.feicuiedu.android.expressage;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenyan on 2016/11/9.
 */

public class MyAsyncTask extends AsyncTask<String,Void,String> {

    private static final String TAG = "MyAsyncTask";

    private Context context;

    private Map<String,Object> resutlMap = new HashMap<>();
    private List<Map<String,String>> result = new ArrayList<>();
    private ListView lvData;
    private ListAdapter lvAdapter = new BaseAdapter() {

        @Override
        public int getCount() {
            return result.size();
        }

        @Override
        public Map<String,String> getItem(int position) {
            return result.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater lif = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = lif.inflate(R.layout.listview_item_layout, null);

            TextView tvTime = (TextView) convertView.findViewById(R.id.tv_time);
            TextView tvFTime = (TextView) convertView.findViewById(R.id.tv_ftime);
            TextView tvContent = (TextView) convertView.findViewById(R.id.tv_content);

            Map<String,String> map = getItem(position);
            String time = map.get("time");
            String ftime = map.get("ftime");
            String context = map.get("context");

            tvTime.setText(time);
            tvFTime.setText(ftime);
            tvContent.setText(context);


            return convertView;
        }
    };

    public MyAsyncTask(ListView lvData, Context context) {
        this.lvData = lvData;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        String orderNo = params[0];
        String company = params[1];

        String strUrl = "http://www.kuaidi100.com/query";
        String strParams = "type="+company+"&postid="+orderNo;

        String result = null;
        URL url = null;
        try {
            url = new URL(strUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5000);

            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            httpURLConnection.setRequestMethod("POST");
            // httpURLConnection.setRequestMethod("POST");

            // 获取输出流
            OutputStream out = httpURLConnection.getOutputStream();
            out.write(strParams.getBytes());
            out.flush();

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


        return result;
    }

    @Override
    protected void onPostExecute(String s) {


        Log.d(TAG, "onPostExecute() called with: s = [" + s + "]");


        /*{"message":"ok",
                "nu":"304312442473",
                "ischeck":"0",
                "condition":"00",
                "com":"shunfeng",
                "status":"200",
                "state":"0",
                "data":[
                    {"time":"2016-11-28 13:04:04","ftime":"2016-11-28 13:04:04","context":"快件到达 【济南历城集散中心】"},
                    {"time":"2016-11-28 12:25:08","ftime":"2016-11-28 12:25:08","context":"快件在【济南历下华强营业部】已装车，准备发往 【济南历城集散中心】"},
                    {"time":"2016-11-28 12:22:47","ftime":"2016-11-28 12:22:47","context":"顺丰速运 已收取快件"}
                    ]
        }*/

        // 使用google的Gson解析json字符串
        Gson gson = new Gson();
        resutlMap = gson.fromJson(s,new TypeToken<Map<String,Object>>(){}.getType());

        result = (List<Map<String, String>>) resutlMap.get("data");
        /*try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray array= jsonObject.getJSONArray("data");
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String time = obj.getString("time");
                String ftime = obj.getString("ftime");
                String context = obj.getString("context");

                Map<String,String> tmpMap = new HashMap<>();
                tmpMap.put("time", time);
                tmpMap.put("ftime", ftime);
                tmpMap.put("context", context);

                result.add(tmpMap);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        lvData.setAdapter(lvAdapter);
        //super.onPostExecute(s);
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
