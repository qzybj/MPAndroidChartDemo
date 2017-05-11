package com.example.mpandroidchart;

import android.app.Application;
import android.content.Context;

/**
 * Created by zsc on 2017/2/23.
 */

public class CApplication extends Application {
    public static Context applicationContext;
    public static Application application;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this.getApplicationContext();
        application = this;
    }
}
