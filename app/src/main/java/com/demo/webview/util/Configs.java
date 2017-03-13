package com.demo.webview.util;

/**
 * 用途：
 * Created by milk on 17/3/13.
 * 邮箱：649444395@qq.com
 */

public interface Configs {
    /**
     * javascript的处理接口
     */
    String URL_PRE = "axd://";
    String JS_INTERFACE = "appJS";
    String JS_INTERFACE_EXECUTE = JS_INTERFACE + ".postMessage";
    String JS_INTERFACE_GETRESULT = JS_INTERFACE + ".getResult";
    int PAGESIZE = 20;

    /**
     * 当返回的对象长度过长则通过变相方式获取结果
     */
    int MAX_H5_ARG_LENGTH = 400;
}
