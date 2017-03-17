package com.demo.at;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.demo.HttpActionHelper;
import com.demo.webview.R;
import com.demo.webview.view.activity.BaseActivity;

/**
 * 用途：
 * Created by milk on 17/3/10.
 * 邮箱：649444395@qq.com
 */

public class MainActivity extends BaseActivity {
    private Button mBtnJsApi;
    private Button mBtnUrlApi;
    private Button mBtnAction;
    @Override
    protected int getRootId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mBtnJsApi = (Button) findViewById(R.id.btn_js);
        mBtnUrlApi = (Button) findViewById(R.id.btn_url);
        mBtnAction= (Button) findViewById(R.id.btn_action);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mBtnJsApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpActionHelper.onEvent(getBaseActivity(),"file:///android_asset/jsApi.html");
            }
        });
        mBtnUrlApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpActionHelper.onEvent(getBaseActivity(),"file:///android_asset/urlApi.html");
            }
        });
        mBtnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpActionHelper.onEvent(getBaseActivity(),"axd://home/go");

            }
        });
    }

    @Override
    public Context getContext() {
        return getContext();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public BaseActivity getBaseActivity() {
        return this;
    }
}
