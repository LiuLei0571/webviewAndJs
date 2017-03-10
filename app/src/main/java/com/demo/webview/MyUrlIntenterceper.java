package com.demo.webview;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.demo.webview.bean.ProtocolBean;
import com.demo.webview.parser.impl.UrlJsonParamParse;
import com.demo.webview.protocol.param.UriBean;
import com.demo.webview.sechuduler.impl.WebSecheduler;
import com.demo.webview.util.WebUtil;
import com.demo.webview.view.widget.CommonWebViewClient;

/**
 * 用途：
 * Created by milk on 17/1/20.
 * 邮箱：649444395@qq.com
 */

public class MyUrlIntenterceper implements CommonWebViewClient.UrlIntercepter {
    private WebSecheduler hybridSechudler;

    public MyUrlIntenterceper(WebInterface webInterface, ProtocolBean[] executes) {
        hybridSechudler = new WebSecheduler(webInterface, executes, UrlJsonParamParse.getInstacne());
    }

    @Override
    public boolean doIntercept(FragmentActivity activity, String url) {
        if (url != null) {
            UriBean uriBean = WebUtil.buildUriBean(url);
            if (uriBean != null) {
                hybridSechudler.doExecute(uriBean, uriBean.getCallback());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        return hybridSechudler.onActivityResult(requestCode, resultCode, data);
    }
}
