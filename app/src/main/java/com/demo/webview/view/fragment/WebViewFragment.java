package com.demo.webview.view.fragment;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.webkit.WebView;

import com.demo.webview.R;
import com.demo.webview.WebInterface;
import com.demo.webview.bean.WebCall;
import com.demo.webview.protocol.UriBean;
import com.demo.webview.view.activity.BaseActivity;

/**
 * 用途：
 * Created by milk on 17/1/16.
 * 邮箱：649444395@qq.com
 */

public class WebViewFragment extends BaseFragment implements WebInterface {
    @Override
    protected int getRootId() {
        return R.layout.fragment_web;
    }

    @Override
    protected void iniView(View view) {

    }

    @Override
    protected void initData(View view) {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public WebView getWebView() {
        return null;
    }

    @Override
    public boolean callWeb(WebCall webCall) {
        return false;
    }

    @Override
    public void doJsExecute(UriBean uriBean) {

    }

    @Override
    public BaseActivity getBaseActivity() {
        return getBaseActivity();
    }

    @Override
    public FragmentActivity getContext() {
        return super.getActivity();
    }
}
