package com.feicuiedu.android.listviewdemo1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultipleActivity extends AppCompatActivity {

    private ListView lvData;

    private Context context;

    private CheckBox chkAll;

    private Button btnShow;
    // 布局加载器
    private LayoutInflater lif;

    private List<Map<String,Object >> listData;

    private List<String> listDataSelected = new ArrayList<>();

    private CompoundButton.OnCheckedChangeListener itemOccl = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            String id = (String) buttonView.getTag();
            if (isChecked) {
                listDataSelected.add(id);
            }
            else {
                listDataSelected.remove(id);
            }
        }
    };

    // 定义并设置数据源
    private BaseAdapter ba = new BaseAdapter() {


        @Override
        public int getCount() {
            return listData.size();
        }

        @Override
        public Map<String,Object > getItem(int position) {
            return listData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder vh = null;

            if (convertView == null) {

                convertView = lif.inflate(R.layout.activity_multiple_item,null);
                vh = new ViewHolder();
                vh.itemChk = (CheckBox) convertView.findViewById(R.id.item_chk);
                vh.itemIv = (ImageView) convertView.findViewById(R.id.item_iv);
                vh.itemTvName = (TextView) convertView.findViewById(R.id.item_tv_name);
                vh.itemTvContent = (TextView) convertView.findViewById(R.id.item_tv_content);
                vh.itemTvEnd = (TextView) convertView.findViewById(R.id.item_tv_end);
                convertView.setTag(vh);
            }
            else {
                vh = (ViewHolder) convertView.getTag();
            }

            Map<String,Object> map = getItem(position);

            String id = (String) map.get("id"+position);
            String name = (String) map.get("name");
            String content = (String) map.get("content");
            String end = (String) map.get("end");
            Boolean isChecked = (Boolean) map.get("isChecked");

            vh.itemChk.setChecked(isChecked);

            vh.itemChk.setOnCheckedChangeListener(itemOccl);
            vh.itemChk.setTag(id);
            vh.itemTvName.setText(name);
            vh.itemTvContent.setText(content);
            vh.itemTvEnd.setText(end);


            return convertView;
        }
    };

    private CompoundButton.OnCheckedChangeListener occl = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            for (Map<String,Object> map :listData) {
                map.put("isChecked",isChecked);
            }

            ba.notifyDataSetChanged();
        }
    };

    private View.OnClickListener showOcl = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (listDataSelected == null || listDataSelected.isEmpty()) {
                Toast.makeText(context,"您没有勾选任意一笔资料",Toast.LENGTH_SHORT).show();
            }
            else {

                StringBuilder sb = new StringBuilder();
                for (String id:listDataSelected) {
                    sb.append(id).append(",");
                }

                Toast.makeText(context,"您勾选的资料id为:"+sb,Toast.LENGTH_SHORT).show();
            }
        }
    };

    private class ViewHolder {
        CheckBox itemChk;
        ImageView itemIv;
        TextView itemTvName;
        TextView itemTvContent;
        TextView itemTvEnd;
    }

    private AdapterView.OnItemClickListener oicl = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple);

        // 初始化布局加载器和上下文引用
        // lif = getLayoutInflater();
        lif = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = this;

        // 控件初始化
        lvData = (ListView) findViewById(R.id.lv_data);
        chkAll = (CheckBox) findViewById(R.id.cb_all);
        btnShow = (Button) findViewById(R.id.btn_show);

        btnShow.setOnClickListener(showOcl);
        chkAll.setOnCheckedChangeListener(occl);
        // 初始化数据源
        listData = getData();

        // 控件和数据源绑定
        lvData.setAdapter(ba);

    }

    private List<Map<String ,Object >> getData() {

        List<Map<String,Object>> result = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("id"+i,"key"+i);
            map.put("name","我是name"+i);
            map.put("content","我的内容是"+i);
            if (i%2 == 0) {
                map.put("end","系统进程");
            }
            else {
                map.put("end","用户进程");
            }
            map.put("isChecked",false);
            result.add(map);
        }
        return result;
    }
}
