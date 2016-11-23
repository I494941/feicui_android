package com.feicuiedu.fragmentdemo;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenyan on 2016/7/13.
 */
public class MyFragment extends Fragment {


    private Button btnContent;
    private String strTxt;
    private int index;
    private String content;
    private Map<String,Object> map = new HashMap<String,Object>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle param = getArguments();
        content = param.getString("content");
        index = param.getInt("index");

        View view = inflater.inflate(R.layout.fg_content, container, false);
        TextView tv = (TextView) view.findViewById(R.id.txt_content);
        btnContent = (Button) view.findViewById(R.id.btn_content);
        final Activity currentActivity = getActivity();

        btnContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("rtnIndex", index+1);
                switch (index){
                    case 0:
                        strTxt = ((TextView)currentActivity.findViewById(R.id.txt_channel)).getText()+"";

                        map.put("rtnContent", "呕心沥血");
                        break;
                    case 1:
                        strTxt = ((TextView)currentActivity.findViewById(R.id.txt_message)).getText()+"";
                        map.put("rtnContent", "鞠躬尽瘁");
                        break;
                    case 2:
                        strTxt = ((TextView)currentActivity.findViewById(R.id.txt_better)).getText()+"";
                        map.put("rtnContent", "精忠报国");
                        break;
                    case 3:
                        strTxt = ((TextView)currentActivity.findViewById(R.id.txt_setting)).getText()+"";
                        map.put("rtnContent", "死而后已");
                        break;
                    default:
                        strTxt = "Nothing";
                        break;
                }

                btnContent.setText(index+"||"+strTxt);
            }
        });

        tv.setText(content);
        return view;
    }

    public void rtnData(IResult result) {

        result.getResult(map);
    }

    public interface IResult {
        public void getResult(Map<String, Object> map);
    }
}
