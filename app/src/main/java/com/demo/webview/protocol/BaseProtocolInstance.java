package com.demo.webview.protocol;

import android.content.Intent;

import com.demo.webview.IAct;
import com.demo.webview.WebInterface;
import com.demo.webview.bean.WebResult;
import com.demo.webview.callback.ICallBack;

/**
 * 用途：
 * Created by milk on 17/3/10.
 * 邮箱：649444395@qq.com
 */

public abstract class BaseProtocolInstance<P> implements IProtocol<P> {
    @Override
    public void doExecute(IAct iAct, ICallBack iCallBack, P params) {
        if (iAct instanceof WebInterface) {
            doExecute((WebInterface) iAct, iCallBack, params);
        }
    }

    public void doExecute(WebInterface iAct, ICallBack iCallBack, P params) {
    }

    @Override
    public int[] useCode() {
        return null;
    }

    @Override
    public void onActivityResult(IAct iAct, ICallBack iCallBack, int requestCode, int resultCode, Intent data) {
        if (iAct instanceof WebInterface) {
            onActivityResult((WebInterface) iAct, iCallBack, requestCode, resultCode, data);
        }
    }

    public void onActivityResult(WebInterface iAct, ICallBack iCallBack, int requestCode, int resultCode, Intent data) {
    }

    protected void callWeb(ICallBack iCallBack, Object... arg) {
        if (iCallBack != null) {
            iCallBack.invoke(arg);
        }
    }

    protected Object success_result(ICallBack iCallBack, Object result) {
        callWeb(iCallBack, WebResult.success_result(result));
        return null;
    }

    protected Object success(ICallBack iCallBack, Object result) {
        callWeb(iCallBack, WebResult.success_result(result));
        return null;
    }

    protected Object success(ICallBack iCallBack ) {
        callWeb(iCallBack, WebResult.success_result(null));
        return null;
    }
    protected Object success_result(ICallBack iCallBack) {
        callWeb(iCallBack, WebResult.success(null));
        return null;
    }

    protected Object fail_arg_error(ICallBack iCallBack) {
        callWeb(iCallBack, WebResult.success_result(WebResult.ERROR_CODE_ARG_EXCEPTION));
        return null;
    }

    protected Object fail_login_error(ICallBack iCallBack) {
        callWeb(iCallBack, WebResult.success_result(WebResult.ERROR_CODE_NOTLOGIN));
        return null;
    }

    protected Object fail_other_error(ICallBack iCallBack) {
        callWeb(iCallBack, WebResult.success_result(WebResult.ERROR_CODE_OTHER));
        return null;
    }

    protected Object fail_cancle_error(ICallBack iCallBack) {
        callWeb(iCallBack, WebResult.success_result(WebResult.ERROR_CODE_CALL_ERROR));
        return null;
    }
}
