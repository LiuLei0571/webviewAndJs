package com.demo.webview.callback;

import com.demo.webview.callback.impl.EmptyCallBack;

import java.util.Objects;

/**
 * 用途：
 * Created by milk on 17/1/16.
 * 邮箱：649444395@qq.com
 */

public interface ICallBack {
    ICallBack empty = new EmptyCallBack();

    void invoke(Object data);

    void invokeAndKeepAlive(Object data);
}
