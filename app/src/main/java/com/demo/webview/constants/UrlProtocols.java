package com.demo.webview.constants;

import com.demo.webview.bean.ProtocolBean;
import com.demo.webview.protocol.impl.jsapi.LoginExecute;

/**
 * 用途：
 * Created by milk on 17/3/10.
 * 邮箱：649444395@qq.com
 */

public interface UrlProtocols {
    ProtocolBean login=ProtocolBean.buildProtocol(LoginExecute.class,"login").module("user");

}
