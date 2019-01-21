package com.ice.restring.application;

import android.app.Application;

import com.ice.restring.R;

public class TestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setTheme(R.style.Theme_AppCompat);
    }
}
