package com.demo.webview.protocol.impl.jsapi;

import com.demo.at.LoginActivity;
import com.demo.webview.WebInterface;
import com.demo.webview.callback.ICallBack;
import com.demo.webview.protocol.BaseProtocolInstance;
import com.demo.webview.protocol.param.PhoneParam;

import java.util.HashMap;
import java.util.Map;

/**
 * 用途：
 * Created by milk on 17/3/10.
 * 邮箱：649444395@qq.com
 */

public class LoginExecute extends BaseProtocolInstance<PhoneParam> {
    @Override
    public void doExecute(WebInterface iAct, final ICallBack iCallBackm, PhoneParam params) {
        super.doExecute(iAct, iCallBackm, params);
        final Map<String, String> userMap = new HashMap<>();
        LoginActivity.getInstace(iAct, new LoginActivity.LoginListenter() {
            @Override
            public void successUser(String phone, String pwd) {
                userMap.put("uid", phone);
                userMap.put("pwd", pwd);
                success(iCallBackm, userMap);
            }

            @Override
            public void fail() {

            }
        });
    }
}
