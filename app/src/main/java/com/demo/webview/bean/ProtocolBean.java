package com.demo.webview.bean;

import com.demo.webview.protocol.IProtocol;

/**
 * 用途：
 * Created by milk on 17/1/16.
 * 邮箱：649444395@qq.com
 */

public class ProtocolBean {
    public ProtocolBean() {
    }

    private Class executeCls;
    private String defaultCallback;
    private String method;
    private String module;
    private boolean postMain = true;
    private boolean needLogin = false;
    private boolean checkParam = false;

    public static ProtocolBean buildProtocol(Class<? extends IProtocol> executeCls, String method) {
        ProtocolBean bean = new ProtocolBean();
        bean.executeCls = executeCls;
        bean.method = method;
        return bean;
    }

    public Class getExecuteCls() {
        return executeCls;
    }

    public void setExecuteCls(Class executeCls) {
        this.executeCls = executeCls;
    }

    public String getDefaultCallback() {
        return defaultCallback;
    }

    public ProtocolBean setDefaultCallback(String defaultCallback) {
        this.defaultCallback = defaultCallback;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public ProtocolBean setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getModule() {
        return module;
    }

    public ProtocolBean module(String module) {
        this.module = module;
        return this;
    }

    public boolean isPostMain() {
        return postMain;
    }

    public ProtocolBean setPostMain(boolean postMain) {
        this.postMain = postMain;
        return this;
    }

    public boolean isNeedLogin() {
        return needLogin;
    }

    public ProtocolBean setNeedLogin(boolean needLogin) {
        this.needLogin = needLogin;
        return this;
    }

    public boolean isCheckParam() {
        return checkParam;
    }

    public ProtocolBean setCheckParam(boolean checkParam) {
        this.checkParam = checkParam;
        return this;
    }
}
