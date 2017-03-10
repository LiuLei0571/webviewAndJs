package com.demo.webview;

import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.demo.webview.bean.ProtocolBean;
import com.demo.webview.parser.impl.JsonParser;
import com.demo.webview.protocol.param.UriBean;
import com.demo.webview.sechuduler.impl.WebSecheduler;

/**
 * 用途：
 * Created by milk on 17/1/18.
 * 邮箱：649444395@qq.com
 */

public class WebPlugin {
    private WebSecheduler hybridSecheduler;

    public WebPlugin(WebInterface webInterface, ProtocolBean[] execute) {
        hybridSecheduler = new WebSecheduler(webInterface, execute, JsonParser.instace());
    }

    @JavascriptInterface
    public String postMessage(final String module, final String method, final String params, final String callback) {
        Log.d("postMessage", "module:" + module + "method:" + method + "params:" + params);
        UriBean uriBean = UriBean.newUriBean(module, method, params);
        hybridSecheduler.doExecute(uriBean, callback);
        return null;
    }

    @JavascriptInterface
    public String getResult(String params) {
        String result = null;
        if (params != null) {
            result = WebResultsStorage.read(params);
        }
        return result;
    }

    public void doExecute(UriBean uriBean) {
        hybridSecheduler.doExecute(uriBean, uriBean.getCallback());
    }

    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        return hybridSecheduler.onActivityResult(requestCode, resultCode, data);
    }
}
