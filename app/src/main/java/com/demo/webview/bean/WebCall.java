package com.demo.webview.bean;

import android.util.Log;

/**
 * 用途：
 * Created by milk on 17/1/16.
 * 邮箱：649444395@qq.com
 */

public class WebCall {
    public String func;
    public Object[] args;
    public String ext;
    public String pre;

    public WebCall() {
    }

    public WebCall setArgs(Object[] args) {
        this.args = args;
        return this;
    }

    public WebCall setExt(String ext) {
        this.ext = ext;
        return this;
    }

    public WebCall setPre(String pre) {
        this.pre = pre;
        return this;
    }

    public static WebCall newWebCall(String func, Object... results) {
        WebCall webCall = null;
        if (func != null) {
            webCall = new WebCall();
            webCall.func = func;
            webCall.args = results;
        } else {
            Log.e("h5", "fun is null");
        }
        return webCall;
    }

    public WebCall eval() {
        setPre("eval('('(+").setExt("+')')");
        return this;
    }
}
