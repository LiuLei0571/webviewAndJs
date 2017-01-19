package com.demo.webview.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.MimeTypeMap;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.demo.webview.R;
import com.demo.webview.WebInterface;
import com.demo.webview.WebResultsStorage;
import com.demo.webview.bean.WebCall;
import com.demo.webview.protocol.UriBean;
import com.demo.webview.view.activity.BaseActivity;
import com.demo.webview.view.widget.CommonWebChromeClient;
import com.demo.webview.view.widget.CommonWebView;
import com.demo.webview.view.widget.CommonWebViewClient;

import java.util.HashMap;
import java.util.Map;

/**
 * 用途：
 * Created by milk on 17/1/16.
 * 邮箱：649444395@qq.com
 */

public class WebViewFragment extends BaseFragment implements WebInterface {
    private CommonWebView mWebView;
    private ProgressBar mProgressBar;
    private String mUrl;
    private String fromType;
    private String referer;
    private WebInterface mWebInterface;
    private FragmentActivity mActivity;
    private CommonWebChromeClient mWebChromeClient;
    private CommonWebViewClient mWebViewClient;
    private Map<String, String> title_map = new HashMap<>();
    private boolean isOnreceiverTitle = false;
    private boolean isPaused = false;
    private boolean isOnBack = false;

    @Override
    protected int getRootId() {
        return R.layout.fragment_web;
    }

    @Override
    protected void iniView(View view) {
        getActivity().getWindow().setFormat(PixelFormat.TRANSLUCENT);
        WebResultsStorage.onCreate();
        mWebView = (CommonWebView) view.findViewById(R.id.web_view);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress);
        mWebView.setLongClickable(true);
        mWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                downFile(url, mimetype);
            }
        });
        WebSettings settings = mWebView.getSettings();
        settings.setUserAgentString("This is my app's demo");
        settings.setGeolocationEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setSupportZoom(true);
        settings.setTextSize(WebSettings.TextSize.NORMAL);
        String appCachePath = getActivity().getCacheDir().getAbsolutePath();
        settings.setAppCachePath(appCachePath);
        settings.setAllowFileAccess(true);
        mActivity = getActivity();
        mWebInterface = this;
    }


    @Override
    protected void initData(View view) {
        Bundle mBunlde = getArguments();
        mUrl = mBunlde.getString("url");
        fromType = mBunlde.getString("fromType");
        referer = mBunlde.getString("referer");
        mWebChromeClient = new CommonWebChromeClient(mActivity, mProgressBar) {
            @Override
            protected void injectJavascriptInterfaces() {
                mWebView.injectJavascriptInterfaces();
            }

            @Override
            protected boolean handleJsInterface(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                return mWebView.handleJsInterface(view, url, message, defaultValue, result);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                if (isPaused) {
                    result.cancel();
                    return false;
                }
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (mListener != null) {
                    mListener.setTitle(title);
                    String url = view.getUrl();
                    if (url != null) {
                        title_map.put(url, title);
                        isOnreceiverTitle = true;
                    }
                }

            }
        };
        mWebChromeClient.setListener(new CommonWebChromeClient.OnValueCallBackListener() {
            @Override
            public void callBack(ValueCallback<Uri[]> uploadMsg) {
                //获取照片
            }

            @Override
            public void callBack2(ValueCallback<Uri> uploadMsg) {
                //获取照片
            }
        });
        mWebViewClient = new CommonWebViewClient(mProgressBar, mActivity, this) {
            @Override
            protected void injectJavascriptInterfaces() {
                mWebView.injectJavascriptInterfaces();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                isOnreceiverTitle = false;
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (isOnreceiverTitle && title_map.containsKey(url)) {
                    String title = title_map.get(url);
                    if (mListener != null) {
                        mListener.setTitle(title);
                    }
                    isOnreceiverTitle = true;
                }
            }
        };
        mWebView.setWebChromeClient(mWebChromeClient);
        mWebView.setWebViewClient(mWebViewClient);
    }

    public void loadUrl(String url, Map<String, String> head) {
        if (mWebView != null) {
            mWebView.loadUrl(url, head);
        }
    }

    public void commonLoadUrl(String url) {
        mWebView.commonLoadUrl(url);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        if (context instanceof OnReceivedTitleListener) {
            mListener = (OnReceivedTitleListener) context;
        }
        super.onAttach(context);
    }

    @Override
    public void onPause() {
        if (mWebView != null) {
            mWebView.onPause();
        }
        isPaused = true;
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (mWebView != null) {
            mWebView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public WebView getWebView() {
        return mWebView;
    }

    @Override
    public boolean callWeb(WebCall webCall) {
        return false;
    }

    @Override
    public void doJsExecute(UriBean uriBean) {
        getJsExecute(uriBean);
    }

    @Override
    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    @Override
    public FragmentActivity getContext() {
        return super.getActivity();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    //工具类

    /**
     * 下载h5中的文件
     *
     * @param url
     * @param mimetype
     */
    private void downFile(String url, String mimetype) {
        if (url.toLowerCase().endsWith(".apk")) {
            if (MimeTypeMap.getSingleton().getMimeTypeFromExtension(".apk").equals(".apk")) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        }
    }

    public void getJsExecute(UriBean uriBean) {

    }

    private OnReceivedTitleListener mListener;

    public interface OnReceivedTitleListener {
        void setTitle(String title);
    }
}
