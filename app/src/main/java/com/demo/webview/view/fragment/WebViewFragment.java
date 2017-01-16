package com.demo.webview.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.MimeTypeMap;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.demo.webview.R;
import com.demo.webview.WebInterface;
import com.demo.webview.bean.WebCall;
import com.demo.webview.protocol.UriBean;
import com.demo.webview.view.activity.BaseActivity;
import com.demo.webview.view.widget.CommonWebView;

/**
 * 用途：
 * Created by milk on 17/1/16.
 * 邮箱：649444395@qq.com
 */

public class WebViewFragment extends BaseFragment implements WebInterface {
    private CommonWebView mWebView;
    private ProgressBar mProgressBar;

    @Override
    protected int getRootId() {
        return R.layout.fragment_web;
    }

    @Override
    protected void iniView(View view) {
        mWebView = (CommonWebView) view.findViewById(R.id.web_view);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress);
        mWebView.setLongClickable(true);
        mWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                downFile(url, mimetype);
            }
        });

    }


    @Override
    protected void initData(View view) {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public WebView getWebView() {
        return null;
    }

    @Override
    public boolean callWeb(WebCall webCall) {
        return false;
    }

    @Override
    public void doJsExecute(UriBean uriBean) {

    }

    @Override
    public BaseActivity getBaseActivity() {
        return getBaseActivity();
    }

    @Override
    public FragmentActivity getContext() {
        return super.getActivity();
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
}
