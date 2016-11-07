package com.feicuiedu.android.checkboxdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cb1 = (CheckBox) findViewById(R.id.cb1);

        cb2 = (CheckBox) findViewById(R.id.cb2);
        cb3 = (CheckBox) findViewById(R.id.cb3);

        /*cb1.setOnCheckedChangeListener(occl);
        cb2.setOnCheckedChangeListener(occl);
        cb3.setOnCheckedChangeListener(occl);*/

        cb1.setOnClickListener(ocl);
        cb2.setOnClickListener(ocl);
        cb3.setOnClickListener(ocl);
    }

    private CompoundButton.OnCheckedChangeListener occl = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            switch(buttonView.getId()) {
                case R.id.cb1:

                    Toast.makeText(MainActivity.this,"cb1.isChecked="+isChecked,Toast.LENGTH_SHORT).show();

                    if (buttonView.isChecked()) {
                        cb2.setChecked(true);
                        cb3.setChecked(true);
                    }
                    else {
                        cb2.setChecked(false);
                        cb3.setChecked(false);
                    }

                    break;
                case R.id.cb2:
                    Toast.makeText(MainActivity.this,"cb2.isChecked="+isChecked,Toast.LENGTH_SHORT).show();
                    break;
                case R.id.cb3:
                    Toast.makeText(MainActivity.this,"cb3.isChecked="+isChecked,Toast.LENGTH_SHORT).show();
                    break;

            }

        }
    };

    private View.OnClickListener ocl = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            CheckBox cb = (CheckBox) v;
            switch(v.getId()) {
                case R.id.cb1:

                    Toast.makeText(MainActivity.this,"cb1.isChecked="+cb.isChecked(),Toast.LENGTH_SHORT).show();

                    if (cb.isChecked()) {
                        cb2.setChecked(true);
                        cb3.setChecked(true);
                    }
                    else {
                        cb2.setChecked(false);
                        cb3.setChecked(false);
                    }

                    break;
                case R.id.cb2:
                    Toast.makeText(MainActivity.this,"cb2.isChecked="+cb.isChecked(),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.cb3:
                    Toast.makeText(MainActivity.this,"cb3.isChecked="+cb.isChecked(),Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };
}
