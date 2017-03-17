package com.demo.webview.protocol.impl.jsapi.webview;

import android.webkit.WebView;

import com.demo.webview.WebInterface;
import com.demo.webview.callback.ICallBack;
import com.demo.webview.protocol.BaseProtocolInstance;

/**
 * 用途：
 * Created by milk on 17/3/17.
 * 邮箱：649444395@qq.com
 */

public class GoBackExecute extends BaseProtocolInstance {
    @Override
    public void doExecute(WebInterface iAct, ICallBack iCallBack, Object params) {
        WebView webView = iAct.getWebView();
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            iAct.getActivity().finish();
        }
    }
}
