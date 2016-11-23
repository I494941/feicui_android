package com.feicuiedu.fragmengdemo1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by chenyan on 2016/7/18.
 */
public class BtnListener implements View.OnClickListener {

    private FragmentActivity context;
    private String strContext;

    public BtnListener(FragmentActivity context,String strContext) {
        this.context = context;
        this.strContext = strContext;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_main:

                BtnFragment bf = new BtnFragment();
                FragmentManager fm = context.getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Bundle bd = new Bundle();
                bd.putString("txt",strContext);
                bf.setArguments(bd);
                ft.add(R.id.ly_main, bf);

                ft.commit();

                break;
            case R.id.btn_frg:

                rtnData(rtnInf);
                break;
            default:

                Toast.makeText(context, "", Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void rtnData(IResult result) {
        result.getData(strContext);
    }

    public interface IResult{
        public void getData(String value);
    }

    private BtnListener.IResult rtnInf = new BtnListener.IResult() {
        @Override
        public void getData(String value) {
            Log.d("value",value);
            Button btn = (Button) context.findViewById(R.id.btn_main);
            btn.setText(value);
        }
    };
}
