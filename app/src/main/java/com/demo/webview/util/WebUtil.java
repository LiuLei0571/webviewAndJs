package com.demo.webview.util;

import android.text.TextUtils;
import android.util.Log;

import com.demo.webview.bean.WebCall;
import com.demo.webview.protocol.param.UriBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 用途：
 * Created by milk on 17/1/16.
 * 邮箱：649444395@qq.com
 */

public class WebUtil {
    private static final String URL_PRE = "axd://";
    public static String buildJsUrl(WebCall webCall){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("javascript:try{");
        return stringBuilder.toString();
    }
    /**
     * 调整url
     *
     * @param url
     * @return
     */
    public static String addInfoToUrl(String url) {
        Log.d("url1 : ", url);
        String urlLower = url.toLowerCase();
        if (urlLower.startsWith("http")) {
            String uid = Strings.NULL_STR;
            String sign = Strings.NULL_STR;
            // 判断登录信息,如果登录的话拼接URL
//            if (UserHelper.isLogin()) {
//                User user = UserHelper.getUser();
//                uid = user.getUserId();
//                sign = user.getSign();
//            }
            if (url.contains("?")) {
                if (!url.contains("&uid=") && !url.contains("?uid=")) {
                    url = url + "&uid=" + uid;
                }
                if (!url.contains("&sign=") && !url.contains("?sign=")) {
                    url = url + "&sign=" + sign;
                }
            } else {
                url = url + "?uid=" + uid + "&sign=" + sign;
            }
        }
        Log.d("url2 : ", url);
        return url;
    }

    public static boolean isNormalUrl(String url) {
        if (url.startsWith("http://") || url.startsWith("https://")) {
            return true;
        }
        return false;
    }

    public static UriBean buildUriBean(String url) {
        if (!url.startsWith(URL_PRE)) {
            return null;
        }
        int q = url.indexOf("?");
        String moduleAction;
        String params = null;
        if (q != -1) {
            moduleAction = url.substring(URL_PRE.length(), q);
            params = url.substring(q + 1);
        } else {
            moduleAction = url.substring(URL_PRE.length());
        }
        String[] moduleActionArr = moduleAction.split("/");
        String module = null;
        String action = null;
        if (moduleActionArr.length >= 2) {
            module = moduleActionArr[0];
            action = moduleActionArr[1];
        } else {
            action = moduleActionArr[0];
        }
        if (TextUtils.isEmpty(action)) {
            Log.e("this action is not support (url= %s )", url);
            return null;
        }
        return UriBean.newUriBean(module, action, params);
    }

    public static Type getSuperClassParam(Class cls) {
        Type superClassType = cls.getGenericSuperclass();
        if (superClassType != null && superClassType instanceof ParameterizedType) {
            ParameterizedType parameterized = (ParameterizedType) superClassType;
            Type[] args = parameterized.getActualTypeArguments();
            if (args != null && args.length > 0) {
                return args[0];
            }
        }
        return null;
    }
}
