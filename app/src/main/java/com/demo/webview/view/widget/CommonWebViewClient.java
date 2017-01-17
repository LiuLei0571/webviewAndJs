package com.demo.webview.view.widget;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * 用途：
 * Created by milk on 17/1/16.
 * 邮箱：649444395@qq.com
 */

public abstract class CommonWebViewClient extends WebViewEx.WebViewClientEx {
    private UrlIntercepter mUrlIntercepter;
    private ProgressBar mProgressBar;
    private FragmentActivity mActivity;
    private Fragment mFragment;

    public CommonWebViewClient(ProgressBar progressBar, FragmentActivity activity, Fragment fragment) {
        mProgressBar = progressBar;
        mActivity = activity;
        mFragment = fragment;
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.d("shouldOverrideUrlLoading", url);
        if (mUrlIntercepter != null && mUrlIntercepter.doIntercept(mActivity, url)) {
            return true;
        } else if (url.startsWith("http://") || url.startsWith("https://")) {
            ((CommonWebView) view).loadUrl(url, false);
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    public void setUrlIntercepter(UrlIntercepter urlIntercepter) {
        mUrlIntercepter = urlIntercepter;
    }

    public interface UrlIntercepter {
        boolean doIntercept(FragmentActivity activity, String url);

        boolean onActivityResult(int requestCode, int resultCode, Intent data);
    }
}
