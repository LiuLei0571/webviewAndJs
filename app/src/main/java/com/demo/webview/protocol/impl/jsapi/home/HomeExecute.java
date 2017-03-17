package com.demo.webview.protocol.impl.jsapi.home;

import android.content.Intent;

import com.demo.at.HomeActivity;
import com.demo.webview.IAct;
import com.demo.webview.callback.ICallBack;
import com.demo.webview.protocol.BaseProtocolInstance;

/**
 * 用途：
 * Created by milk on 17/3/17.
 * 邮箱：649444395@qq.com
 */

public class HomeExecute extends BaseProtocolInstance{
    @Override
    public void doExecute(IAct iAct, ICallBack iCallBack, Object params) {
        super.doExecute(iAct, iCallBack, params);
        iAct.startActivity(new Intent(iAct.getActivity(),HomeActivity.class));

    }
}
