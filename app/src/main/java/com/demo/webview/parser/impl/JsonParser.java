package com.demo.webview.parser.impl;

import com.demo.webview.util.JsonHelper;
import com.demo.webview.parser.IParamParser;

import java.lang.reflect.Type;

/**
 * 用途：
 * Created by milk on 17/1/18.
 * 邮箱：649444395@qq.com
 */

public class JsonParser implements IParamParser {
    private static JsonParser instance;

    public JsonParser() {
    }

    public static JsonParser instace() {
        if (instance == null) {
            instance = new JsonParser();
        }
        return instance;
    }

    /**
     * 将name=login&&pass=123转换为对象
     *
     * @param type
     * @param paramStr
     * @param <T>
     * @return
     */
    @Override
    public <T> T getParam(Type type, String paramStr) {

        if (type == String.class) {
            return (T) paramStr;
        }
        return JsonHelper.fromJson(paramStr, type);
    }
}
