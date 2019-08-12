package com.fanxing.wanandroid.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fanxing.wanandroid.R;
import com.fanxing.wanandroid.base.BaseActivity;
import com.fanxing.wanandroid.ui.fragment.ShareDialogFragment;
import com.fanxing.wanandroid.util.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 繁星
 */
public class WebViewActivity extends BaseActivity {
    public final static String WEB_VIEW_TITLE = "WebViewTitle";
    public final static String WEB_VIEW_URL = "WebViewUrl";
    private final String APP_CACAHE_DIRNAME = "webViewCache";
    @BindView(R.id.btn_back)
    ImageButton btnBack;
    @BindView(R.id.btn_collect)
    ImageButton btnCollect;
    @BindView(R.id.btn_share)
    ImageButton btnShare;
    @BindView(R.id.web_frame)
    FrameLayout webFrame;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private Unbinder mBind;
    private WebView mWebView;
    private String mWebUrl;
    private String mTitle;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_webview;
    }

    @Override
    public void initView() {
        //ButterKnife绑定
        mBind = ButterKnife.bind(this);
        Intent intent = getIntent();
        mTitle = intent.getStringExtra(WEB_VIEW_TITLE);
        tvTitle.setText(mTitle);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        mWebView = new WebView(getApplicationContext());
        setDefaultWebSettings(mWebView);
        mWebView.setLayoutParams(params);
        webFrame.addView(mWebView);
        mWebUrl = intent.getStringExtra(WEB_VIEW_URL);
        mWebView.loadUrl(mWebUrl);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        btnBack.setOnClickListener(this);
        btnShare.setOnClickListener(this);
        btnCollect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v, int id) {
        switch (id) {
            case R.id.btn_share:
                ShareDialogFragment dialog = new ShareDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString(WEB_VIEW_TITLE,mTitle);
                bundle.putString(WEB_VIEW_URL,mWebUrl);
                dialog.setArguments(bundle);
                dialog.show(getSupportFragmentManager(), "shareDialog");
                break;
            case R.id.btn_collect:

                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();

            ((FrameLayout) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
        //解除绑定防止内存溢出
        mBind.unbind();
    }

    public void setDefaultWebSettings(WebView webView) {
        WebSettings webSettings = webView.getSettings();

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                LogUtil.e(newProgress + "");
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }
            }
        });
        //5.0以上开启混合模式加载
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        //允许js代码
        webSettings.setJavaScriptEnabled(true);
        //允许SessionStorage/LocalStorage存储
        webSettings.setDomStorageEnabled(true);
        //开启缩放
        webSettings.setBuiltInZoomControls(true);
        //禁用缩放按钮图标图标
        webSettings.setDisplayZoomControls(false);
        //禁用文字缩放
        webSettings.setTextZoom(100);
        //10M缓存，api 18后，系统自动管理。
        webSettings.setAppCacheMaxSize(10 * 1024 * 1024);
        //允许缓存，设置缓存位置
        webSettings.setAppCacheEnabled(true);
        String cacheDirPath = getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME;
        webSettings.setAppCachePath(cacheDirPath);
        //允许WebView使用File协议
        webSettings.setAllowFileAccess(true);
        //不保存密码
        webSettings.setSavePassword(false);
        //设置UA
        webSettings.setUserAgentString(webSettings.getUserAgentString() + getPackageName());
        //移除部分系统JavaScript接口
        //自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
    }


}
