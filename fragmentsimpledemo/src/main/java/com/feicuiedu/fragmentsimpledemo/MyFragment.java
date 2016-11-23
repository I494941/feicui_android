package com.feicuiedu.fragmentsimpledemo;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyFragment extends Fragment {

    private Button btn1;
    private Button btn2;
    private TextView txt;

    private LinearLayout line1;

    private FragmentManager fm;
    private FragmentTransaction ft;
    private Fragment fTmp = null;
    private int tmpId;


    /*private int indexAdd = 0;
    private int indexReplace = 0;*/
    private View.OnClickListener ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button) v;
            txt.setText(b.getText());


            ft = fm.beginTransaction();


            if (v.getId() == R.id.btn1) {
                //Fragment1 f3 = new Fragment1();
                //ft.replace(R.id.line_main_1,f1);

                //ft.add(R.id.line_main_1, f1, String.valueOf(indexAdd));
                //indexAdd ++;

                /*if (!ft.isAddToBackStackAllowed()) {
                    ft.add(R.id.line_main_1,fTmp);
                }*/


                Log.d("f1.id>>>>>>>>>>>", fTmp.getId() + "");
            } else {
                //fTmp = new Fragment2();
                //ft.replace(R.id.line_main_1, f2, String.valueOf(indexReplace)).hide(f2);
                //indexReplace++;
                //ft.hide(fTmp);
                //Log.d("f2.id>>>>>>>>>>>>>", fTmp.getId() + "");
                //ft.hide(fTmp);
                //ft.show(fTmp);
                ft.remove(fTmp);
                Log.d("f2.id>>>>>>>>>>>", fTmp.getId() + "");
            }

            //ft.replace(R.id.line_main_1,fTmp);
            ft.addToBackStack(null);
            ft.commit();

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my, container, false);
        btn1 = (Button) view.findViewById(R.id.btn1);
        btn2 = (Button) view.findViewById(R.id.btn2);
        txt = (TextView) view.findViewById(R.id.txt);

        fm = getFragmentManager();
        fTmp = new Fragment1();
        btn1.setOnClickListener(ocl);
        btn2.setOnClickListener(ocl);

        return view;
    }

}
