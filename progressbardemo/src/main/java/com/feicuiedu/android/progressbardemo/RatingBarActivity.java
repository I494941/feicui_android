package com.feicuiedu.android.progressbardemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.Toast;

/**
 * Created by chenyan on 2016/11/1.
 */

public class RatingBarActivity extends AppCompatActivity {

    private RatingBar rb1;
    private RatingBar.OnRatingBarChangeListener obcl = new RatingBar.OnRatingBarChangeListener() {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            Toast.makeText(RatingBarActivity.this,"当前rating是"+rating,Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ratingbar_layout);

        rb1 = (RatingBar) findViewById(R.id.rb1);

        rb1.setOnRatingBarChangeListener(obcl);
    }
}
