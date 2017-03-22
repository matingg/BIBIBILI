package com.mashaoting.bibibili.directplay.dianjitiaozhuanyemian;

import android.os.Build;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.base.BaseoActivity;

import butterknife.InjectView;

public class BannerInfoActivity extends BaseoActivity {


    @InjectView(R.id.banner_webview)
    WebView bannerWebview;
    @InjectView(R.id.activity_banner_info)
    RelativeLayout activityBannerInfo;
    private String stringExtra;


    @Override
    public int getLayoutid() {
        return R.layout.activity_banner_info;

    }

    @Override
    protected void initTitle() {
        stringExtra = getIntent().getStringExtra("intent");
    }

    @Override
    protected void initData() {

        //1.设置支持js
        WebSettings webSettings = bannerWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);//设置支持js
        webSettings.setUseWideViewPort(true);//设置用户双击单击
        webSettings.setBuiltInZoomControls(true);

        //2.设置客户端监听
        bannerWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                progressbar.setVisibility(View.GONE);
            }
        });

        //3.加载路径
        bannerWebview.loadUrl(stringExtra);
    }

    @Override
    protected void initListener() {

    }


}
