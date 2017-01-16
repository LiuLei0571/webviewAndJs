package com.demo.webview.view.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.demo.webview.util.WebUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 用途：
 * Created by milk on 17/1/16.
 * 邮箱：649444395@qq.com
 */

public class CommonWebView extends WebViewEx {
    public CommonWebView(Context context) {
        super(context);
        init();
    }

    public CommonWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CommonWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setWebContentsDebuggingEnabled(true);
        }
    }


    public void loadUrl(String url, boolean needAgent) {
        Map<String, String> head = new HashMap<>();
        head.put("referer", getUrl());
        if (needAgent) {
            loadUrl(url, head);
        } else {
            super.loadUrl(url, head);
        }

    }

    @Override
    public void loadUrl(String url, Map<String, String> head) {
        url = WebUtil.addInfoToUrl(url);
        if (head == null) {
            super.loadUrl(url);
        } else {
            super.loadUrl(url, head);
        }
    }

    @Override
    public void loadUrl(String url) {
        super.loadUrl(url, null);
    }

    @Override
    public void destroy() {
        ViewGroup parent = (ViewGroup) getParent();
        if (parent != null) {
            parent.removeAllViews();
        }
        super.destroy();
    }
}
