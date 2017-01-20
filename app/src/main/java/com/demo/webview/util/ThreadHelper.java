package com.demo.webview.util;

import android.os.Handler;
import android.os.Looper;

/**
 * 线程帮助类
 */
public final class ThreadHelper {

    public final static Handler MAIN = new Handler(Looper.getMainLooper());

    public static boolean inMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }


    public static void postMain(Runnable runnable) {
        MAIN.post(runnable);
    }

    public static void postDelayed(final Runnable runnable, long delayMillis) {
        MAIN.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    runnable.run();
                } catch (Throwable throwable) {
                    LogHelper.task().e(throwable);
                }
            }
        }, delayMillis);
    }

    public static void removeCallbacks(Runnable runnable) {
        MAIN.removeCallbacks(runnable);
    }

}
