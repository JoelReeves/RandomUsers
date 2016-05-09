package com.bromancelabs.randomusers.application;

import android.app.Application;

import com.bromancelabs.randomusers.BuildConfig;

import timber.log.Timber;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
