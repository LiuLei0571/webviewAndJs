package com.demo.webview;

import android.webkit.WebView;

import com.demo.webview.bean.WebCall;
import com.demo.webview.protocol.UriBean;

/**
 * 用途：
 * Created by milk on 17/1/16.
 * 邮箱：649444395@qq.com
 */

public interface WebInterface extends IAct {
    WebView getWebView();
    boolean callWeb (WebCall webCall);
    void doJsExecute(UriBean uriBean);
}
