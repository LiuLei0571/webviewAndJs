package com.demo.webview.view.widget;

import android.app.Activity;
import android.net.Uri;
import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * 用途：
 * Created by milk on 17/1/16.
 * 邮箱：649444395@qq.com
 */

public abstract class CommonWebChromeClient extends WebViewEx.WebChromeClientEx {
    ProgressBar mProgressBar;
    Activity mActivity;
    OnValueCallBackListener mListener;

    public CommonWebChromeClient(Activity activity, ProgressBar progressBar) {
        mActivity = activity;
        mProgressBar = progressBar;
    }

    @Override
    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
        callback.invoke(origin, true, false);
        super.onGeolocationPermissionsShowPrompt(origin, callback);
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        if (mProgressBar != null) {
            mProgressBar.setProgress(newProgress);
        }
    }

    public void onFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        openFileChooser(uploadMsg);
    }

    // >3.0
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        openFileChooser(uploadMsg);

    }

    //<3.0
    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        if (mListener != null) {
            mListener.callBack2(uploadMsg);
        }
    }


    public void setListener(OnValueCallBackListener listener) {
        mListener = listener;
    }


    public interface OnValueCallBackListener {
        void callBack(ValueCallback<Uri[]> uploadMsg);

        void callBack2(ValueCallback<Uri> uploadMsg);

    }
}
