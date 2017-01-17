package com.demo.webview;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 用途：键值临时保存
 * Created by milk on 17/1/17.
 * 邮箱：649444395@qq.com
 */

public class WebResultsStorage {
    private final static Map<String, String> values = new HashMap<>();
    private static int refrence = 0;

    public static synchronized String put(String value) {
        String key = newKey();
        while (value.contains(key)) {
            key = newKey();
        }
        values.put(key, value);
        return key;
    }

    public static synchronized String read(String key) {
        String result = null;
        try {
            result = values.get(key);
        } catch (Exception e) {
        }
        values.remove(key);
        return result;
    }

    /**
     * 自动生成全局唯一标识符
     *
     * @return
     */
    public static String newKey() {
        return UUID.randomUUID().toString();
    }

    public static synchronized void onCreate() {
        refrence++;
    }

    public static synchronized void destory() {
        refrence--;
        if (refrence == 0) {
            values.clear();
        }
    }
}
