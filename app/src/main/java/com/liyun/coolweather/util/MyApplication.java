package com.liyun.coolweather.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

public class MyApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
