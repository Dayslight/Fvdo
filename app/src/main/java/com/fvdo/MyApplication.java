package com.fvdo;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by bijaybogati on 4/9/16.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);

    }
}
