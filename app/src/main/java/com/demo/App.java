package com.demo;

import android.app.Application;
import android.content.Context;

/**
 * 用途：
 * Created by milk on 17/3/14.
 * 邮箱：649444395@qq.com
 */

public class App extends Application {
    private static Context mContext;
    boolean unLogin = false;
    private static App _instance;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }


    public static Context getContext() {
        return mContext;
    }
}
