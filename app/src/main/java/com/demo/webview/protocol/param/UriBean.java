package com.demo.webview.protocol.param;

import com.demo.webview.bean.ProtocolBean;

/**
 * 用途：
 * Created by milk on 17/1/16.
 * 邮箱：649444395@qq.com
 */

public class UriBean {
    private String module;
    private String method;
    private String params;
    private String callback;

    public static UriBean newUriBean(String module, String method, String params) {
        UriBean uriBean = new UriBean();
        uriBean.setModule(module);
        uriBean.setMethod(method);
        uriBean.setParams(params);
        return uriBean;
    }

    public static UriBean newUriBean(ProtocolBean protocolBean, String params) {
        UriBean uriBean = new UriBean();
        uriBean.setMethod(protocolBean.getMethod());
        uriBean.setMethod(protocolBean.getModule());
        uriBean.setParams(params);
        return uriBean;

    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }
}
