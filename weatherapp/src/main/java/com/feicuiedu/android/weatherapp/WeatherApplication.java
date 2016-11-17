package com.feicuiedu.android.weatherapp;

import android.app.Application;

import org.xutils.x;

/**
 * Created by chenyan on 2016/11/17.
 */

public class WeatherApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
