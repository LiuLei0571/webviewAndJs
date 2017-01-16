package com.demo.webview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.demo.webview.view.activity.BaseActivity;

/**
 * 用途：
 * Created by milk on 17/1/16.
 * 邮箱：649444395@qq.com
 */

public interface IAct {
    Context getContext();

    Activity getActivity();

    BaseActivity getBaseActivity();

    void startActivity(Intent intent);

    void startActivityForResult(Intent intent ,int requestCode);
}
