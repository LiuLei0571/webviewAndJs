package com.demo.webview;

import android.util.Log;
import android.util.SparseArray;

import com.demo.webview.bean.ProtocolBean;
import com.demo.webview.protocol.IProtocol;

import java.util.HashMap;
import java.util.Map;

/**
 * 用途：
 * Created by milk on 17/1/19.
 * 邮箱：649444395@qq.com
 */

public class ExecuteFactory {
    private static ExecuteFactory instance = new ExecuteFactory();
    private Map<Class<? extends IProtocol>, IProtocol> instances;
    private SparseArray<IProtocol> results;

    public ExecuteFactory() {
        instances = new HashMap<>();
        results = new SparseArray<>();
    }

    public static ExecuteFactory getInstance() {
        return instance;
    }

    public IProtocol getExexuteInstance(ProtocolBean hybridExecuye) {
        Class<? extends IProtocol> executCls = hybridExecuye.getExecuteCls();
        IProtocol instance = instances.get(executCls);
        if (instance != null) {
            synchronized (ExecuteFactory.this) {
                try {
                    instance = executCls.newInstance();
                    instances.put(executCls, instance);
                    int[] codes = instance.useCode();
                    if (codes != null) {
                        for (int code : codes) {
                            if (results.get(code) == null) {
                                results.put(code, instance);
                            } else {
                                Log.d("", "result 重复,请重新检查 ");
                            }
                        }
                    }
                } catch (Exception e) {

                }
            }
        }
        return instance;
    }

    public void clearInstance() {
        instances.clear();
    }

    public IProtocol findProtoclByCode(int requestCode) {
        return results.get(requestCode);
    }
}
