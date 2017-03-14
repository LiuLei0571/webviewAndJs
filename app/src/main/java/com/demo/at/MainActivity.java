package com.demo.at;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.demo.webview.R;
import com.demo.webview.view.activity.BaseActivity;
import com.demo.webview.view.activity.WebViewActivity;

/**
 * 用途：
 * Created by milk on 17/3/10.
 * 邮箱：649444395@qq.com
 */

public class MainActivity extends BaseActivity {
    private Button mBtnJsApi;
    private Button mBtnUrlApi;

    @Override
    protected int getRootId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mBtnJsApi = (Button) findViewById(R.id.btn_js);
        mBtnUrlApi = (Button) findViewById(R.id.btn_url);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mBtnJsApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url", "file:///android_asset/jsApi.html");
                startActivity(intent);
            }
        });
        mBtnUrlApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url", "file:///android_asset/urlApi.html");
                startActivity(intent);
            }
        });
    }
}
