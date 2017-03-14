package com.demo.webview;

import com.demo.webview.bean.ProtocolBean;

import static com.demo.webview.constants.JsApiProtocols.login;
import static com.demo.webview.constants.JsApiProtocols.uploadPickImage;
import static com.demo.webview.constants.JsApiProtocols.uploadWithTakePhoto;
import static com.demo.webview.constants.UrlProtocols.signup;

/**
 * 用途：
 * Created by milk on 17/1/20.
 * 邮箱：649444395@qq.com
 */

public interface Protocols {
    ProtocolBean[] jsProtocols = new ProtocolBean[]{
            login,uploadPickImage,uploadWithTakePhoto
    };
    ProtocolBean[] urlIntercepter = new ProtocolBean[]{
            signup
    };
}
