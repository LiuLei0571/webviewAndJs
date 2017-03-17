package com.demo.webview.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 用途：
 * Created by milk on 17/1/18.
 * 邮箱：649444395@qq.com
 */

public class WebResult<T> {
    public static final int SUCCESS_CODE = 0;
    public static final int ERROR_CODE_CALL_ERROR = -1000;
    public static final int ERROR_CODE_MODULE_NOT_EXISITS = -999;
    public static final int ERROR_CODE_MEHTOD_NOT_EXISTS = -998;
    public static final int ERROR_CODE_ARG_EXCEPTION = -997;
    public static final int ERROR_CODE_NOTLOGIN = -996;
    public static final int ERROR_CODE_CANCEL = -995;
    public static final int ERROR_CODE_OTHER = -1001;
    private int code;
    private String msg;
    private T data;

    public WebResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static WebResult<?> faulure(int code) {
        return faulure(code, null);
    }

    public static WebResult<?> faulure(int code, String msg) {
        if (msg != null) {
            return new WebResult<>(code, msg, null);
        }
        switch (code) {
            case ERROR_CODE_ARG_EXCEPTION:
                msg = "参数非法";
                break;
            case ERROR_CODE_CALL_ERROR:
                msg = "调用格式错误";
                break;
            case ERROR_CODE_CANCEL:
                msg = "用户取消操作";
                break;
            case ERROR_CODE_MEHTOD_NOT_EXISTS:
                msg = "方法未找到";
                break;
            case ERROR_CODE_MODULE_NOT_EXISITS:
                msg = "模块未找到";
                break;
            case ERROR_CODE_NOTLOGIN:
                msg = "用户未登录";
                break;
            case ERROR_CODE_OTHER:
                msg = "操作失败";
                break;
        }
        return new WebResult<>(code, msg, null);
    }

    public static WebResult<Map<String, Object>> success_result(Object result) {
        Map<String, Object> data = new HashMap<>();
        data.put("result", result);
        return new WebResult<>(SUCCESS_CODE, null, data);
    }

    public static <T> WebResult<T> success(T data) {
        return  new WebResult<>(SUCCESS_CODE,null,data);
    }
}
