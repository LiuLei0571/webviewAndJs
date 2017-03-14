package com.demo.webview.protocol.impl.jsapi;

import android.content.Intent;

import com.demo.RequestCodes;
import com.demo.at.SignupActivity;
import com.demo.webview.IAct;
import com.demo.webview.callback.ICallBack;
import com.demo.webview.protocol.BaseProtocolInstance;
import com.demo.webview.protocol.param.PhoneParam;

/**
 * 用途：
 * Created by milk on 17/3/14.
 * 邮箱：649444395@qq.com
 */

public class SignupExecute extends BaseProtocolInstance<PhoneParam> {
    @Override
    public void doExecute(IAct iAct, ICallBack iCallBackm, PhoneParam params) {
        super.doExecute(iAct, iCallBackm, params);
        iAct.startActivity(new Intent(iAct.getActivity(), SignupActivity.class));
    }

    @Override
    public int[] useCode() {
        return new int[]{RequestCodes.REQ_CAMERA};
    }

    @Override
    public void onActivityResult(IAct iAct, ICallBack iCallBack, int requestCode, int resultCode, Intent data) {
        super.onActivityResult(iAct, iCallBack, requestCode, resultCode, data);
        callWeb(iCallBack, "");
    }
}
