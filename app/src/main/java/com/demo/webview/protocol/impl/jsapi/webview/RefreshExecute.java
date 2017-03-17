package com.demo.webview.protocol.impl.jsapi.webview;

import android.webkit.WebView;

import com.demo.webview.WebInterface;
import com.demo.webview.callback.ICallBack;
import com.demo.webview.protocol.BaseProtocolInstance;
import com.demo.webview.protocol.param.WebRefreshParam;
import com.demo.webview.util.WebUtil;

/**
 * 用途：
 * Created by milk on 17/3/17.
 * 邮箱：649444395@qq.com
 */

public class RefreshExecute extends BaseProtocolInstance<WebRefreshParam> {
    @Override
    public void doExecute(WebInterface iAct, ICallBack iCallBack, WebRefreshParam params) {
        if (params == null) {
            fail_arg_error(iCallBack);
        }
        WebView webView=iAct.getWebView();
        String reloadUrl=webView.getUrl();
        if(params.joinLoinInfo){
        webView.loadUrl( WebUtil.refresh(reloadUrl));
        }else {
            webView.loadUrl(reloadUrl);
        }
        success(iCallBack);
    }
}
