package com.feicuiedu.android.expressage;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Context context;

    private EditText edOrder;
    private Spinner spCompany;
    private Button btnQuery;
    private ListView lvData;

    private List<Map<String, String>> lstCompany = new ArrayList<>();

    private String selectedCompany;
    private SpinnerAdapter spAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return lstCompany.size();
        }

        @Override
        public Map<String, String> getItem(int position) {
            return lstCompany.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater lif = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = lif.inflate(R.layout.spinner_item_layout, null);
            TextView tvCompanyName = (TextView) convertView.findViewById(R.id.tv_company_name);
            Map<String, String> map = getItem(position);
            String value = "";
            for (Map.Entry<String, String> entry : map.entrySet()) {
                value = entry.getValue();
            }

            tvCompanyName.setText(value);
            return convertView;
        }
    };
    private View.OnClickListener btnOcl = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            String orderNo = String.valueOf(edOrder.getText());

            MyAsyncTask mat = new MyAsyncTask(lvData,context);
            mat.execute(orderNo,selectedCompany);
        }
    };
    private AdapterView.OnItemSelectedListener spOisl = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Map<String,String> map = lstCompany.get(position);
            String key = "";
            for (Map.Entry<String, String> entry : map.entrySet()) {
                key = entry.getKey();
            }

            selectedCompany = key;

            // Toast.makeText(context,"当先选择中的快递公司为:"+selectedCompany,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initView();
        initData();

        spCompany.setAdapter(spAdapter);
        spCompany.setOnItemSelectedListener(spOisl);
        btnQuery.setOnClickListener(btnOcl);
    }

    private void initView() {

        edOrder = (EditText) findViewById(R.id.ed_order);
        spCompany = (Spinner) findViewById(R.id.sp_company);
        btnQuery = (Button) findViewById(R.id.btn_query);
        lvData = (ListView) findViewById(R.id.lv_data);
    }

    private void initData() {
        lstCompany = getCompanys();

    }

    private List<Map<String, String>> getCompanys() {
        List<Map<String, String>> result = new ArrayList<>();

        Map<String, String> map = new HashMap<>();
        map.put("shentong", "申通");
        result.add(map);

        map = new HashMap<>();
        map.put("ems", "EMS");
        result.add(map);


        map = new HashMap<>();
        map.put("shunfeng", "顺丰");
        result.add(map);

        map = new HashMap<>();
        map.put("yuantong", "圆通");
        result.add(map);

        map = new HashMap<>();
        map.put("zhongtong", "中通");
        result.add(map);

        map = new HashMap<>();
        map.put("yunda", "韵达");
        result.add(map);

        map = new HashMap<>();
        map.put("tiantian", "天天");
        result.add(map);

        map = new HashMap<>();
        map.put("huitongkuaidi", "汇通");
        result.add(map);

        map = new HashMap<>();
        map.put("quanfengkuaidi", "全峰");
        result.add(map);

        map = new HashMap<>();
        map.put("debangwuliu", "德邦");
        result.add(map);

        map = new HashMap<>();
        map.put("zhaijisong", "宅急送");
        result.add(map);

        return result;
    }
}
