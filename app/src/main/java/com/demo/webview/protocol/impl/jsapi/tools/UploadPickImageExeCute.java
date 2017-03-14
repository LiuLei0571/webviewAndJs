package com.demo.webview.protocol.impl.jsapi.tools;

import android.content.Intent;

import com.demo.RequestCodes;
import com.demo.webview.IAct;
import com.demo.webview.callback.ICallBack;
import com.demo.webview.protocol.BaseProtocolInstance;
import com.demo.webview.protocol.param.PickImageParam;

/**
 * 用途：
 * Created by milk on 17/3/14.
 * 邮箱：649444395@qq.com
 */

public class UploadPickImageExecute extends BaseProtocolInstance<PickImageParam> {
    @Override
    public void doExecute(IAct iAct, ICallBack iCallBackm, PickImageParam params) {
        super.doExecute(iAct, iCallBackm, params);
    }

    @Override
    public void onActivityResult(IAct iAct, ICallBack iCallBack, int requestCode, int resultCode, Intent data) {
        super.onActivityResult(iAct, iCallBack, requestCode, resultCode, data);
    }

    @Override
    public int[] useCode() {
        return new int[]{RequestCodes.REQ_PICK};
    }
}
