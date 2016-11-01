package com.feicuiedu.android.progressbardemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.Toast;

/**
 * Created by chenyan on 2016/11/1.
 */

public class SeekBarActivity extends AppCompatActivity {

    private SeekBar sb1;
    private SeekBar.OnSeekBarChangeListener ocl = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Toast.makeText(SeekBarActivity.this,"progress="+progress,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            Toast.makeText(SeekBarActivity.this,"onStartTrackingTouch:"+seekBar.getProgress(),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            Toast.makeText(SeekBarActivity.this,"onStopTrackingTouch:"+seekBar.getProgress(),Toast.LENGTH_SHORT).show();
        }

    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seekbar_layout);

        sb1 = (SeekBar) findViewById(R.id.sb1);
        sb1.setOnSeekBarChangeListener(ocl);
    }
}
