package com.demo.webview.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * 用途：
 * Created by milk on 17/1/16.
 * 邮箱：649444395@qq.com
 */

public abstract class BaseActivity extends FragmentActivity {
    protected BaseActivity mBaseActivity;

    @Override
    protected void onStart() {
        super.onStart();
        mBaseActivity=this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getRootId());
        initView(savedInstanceState);
        initData(savedInstanceState);
    }
    protected FragmentManager getSupportsFragmentManager(){
        return   getSupportFragmentManager();
    }

    protected abstract int getRootId();
    protected abstract void initView(Bundle savedInstanceState);
    protected abstract void initData(Bundle savedInstanceState);

}
