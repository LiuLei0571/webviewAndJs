package com.demo;

import android.content.Intent;

import com.demo.webview.IAct;
import com.demo.webview.Protocols;
import com.demo.webview.parser.impl.UrlJsonParamParse;
import com.demo.webview.protocol.param.UriBean;
import com.demo.webview.sechuduler.impl.ActionScheduler;
import com.demo.webview.util.WebUtil;
import com.demo.webview.view.activity.WebViewActivity;

/**
 * 用途：
 * Created by milk on 17/3/17.
 * 邮箱：649444395@qq.com
 */

public class HttpActionHelper {
    private static ActionScheduler sActionScheduler;
    public static boolean onEvent(IAct iAct,String url){
        if (url == null) {
            return false;
        }
        if(WebUtil.isNormalUrl(url)){
            Intent intent=new Intent(iAct.getActivity(), WebViewActivity.class);
            intent.putExtra("url",url);
            iAct.startActivity(intent);
            return true;
        }
        UriBean uriBean=WebUtil.buildUriBean(url);
        if (uriBean != null) {
            getActionSecheduler().doExecute(iAct,uriBean);
        }
        return false;
    }
    private static  synchronized ActionScheduler getActionSecheduler(){
        if (sActionScheduler == null) {
            sActionScheduler=new ActionScheduler(Protocols.actionProtocols, UrlJsonParamParse.getInstacne());
        }
        return sActionScheduler;
    }
}
