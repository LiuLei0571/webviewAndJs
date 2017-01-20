package com.demo.webview.util;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Type;

/**
 * 用途：
 * Created by milk on 17/1/18.
 * 邮箱：649444395@qq.com
 */

public class JsonHelper {
    public static String toJson(Object bean) {
        if (bean != null) {
            try {
                return JSON.toJSONString(bean);
            } catch (Exception e) {

            }
        }
        return null;
    }

    public static <T> T fromJson(String json, Class<T> bean) {
        if (json != null) {
            try {
                return JSON.parseObject(json, bean);
            } catch (Exception e) {
                Log.d("bean to json", json);
            }
        }
        return null;
    }

    public static <T> T fromJson(String json, Type bean) {
        if (json != null) {
            try {
                return JSON.parseObject(json, bean);
            } catch (Exception e) {
                Log.d("json to bean", json);

            }
        }
        return null;
    }
}
