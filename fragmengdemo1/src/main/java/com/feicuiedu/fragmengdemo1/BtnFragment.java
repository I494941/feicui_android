package com.feicuiedu.fragmengdemo1;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by chenyan on 2016/7/18.
 */
public class BtnFragment extends Fragment {

    private Button btnFrag;

    private String strValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content, container, false);
        btnFrag = (Button) view.findViewById(R.id.btn_frg);
        Bundle bd = getArguments();
        if (bd != null) {
            String str = bd.getString("txt");

            btnFrag.setText(str);
        }

        strValue = btnFrag.getText()+"||女神";
        BtnListener bl = new BtnListener((FragmentActivity) getActivity(),strValue);
        btnFrag.setOnClickListener(bl);
        return view;
    }


}
