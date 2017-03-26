package com.mashaoting.bibibili.directplay.dianjitiaozhuanyemian;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.base.BaseoActivity;
import com.mashaoting.bibibili.utils.ClipboardUtil;

import butterknife.InjectView;

public class BannerInfoActivity extends BaseoActivity {
    @InjectView(R.id.wenben)
    TextView wenben;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @InjectView(R.id.banner_webview)
    WebView bannerWebview;
    private String stringExtra;
    private String tilte;
    @Override
    public int getLayoutid() {
        return R.layout.activity_banner_info;
    }
    @Override
    protected void initTitle() {
        stringExtra = getIntent().getStringExtra("intent");
        tilte = getIntent().getStringExtra("tilte");
        wenben.setText(tilte);
    }
    @Override
    protected void initData() {
        //1.设置支持js
        WebSettings webSettings = bannerWebview.getSettings();
        //设置支持js
        webSettings.setJavaScriptEnabled(true);
        //设置用户双击单击
        webSettings.setUseWideViewPort(true);
        //设置添加缩放按钮
        webSettings.setBuiltInZoomControls(true);
        //设置WebViewClient,如果没有设置，调起系统的浏览器打开新的连接
        bannerWebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                progressbar.setVisibility(View.GONE);
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }
                return true;
            }
        });
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
            }
        });
        //1.添加addJavascriptInterface
        bannerWebview.addJavascriptInterface(new MyJavascriptInterface(), "cyc");
        //3.加载路径
        bannerWebview.loadUrl(stringExtra);
        //用toolbar来设置标题
        toolbar.setTitle(tilte);
        //设置toolbar@
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

    }
    @Override
    protected void initListener() {
    }
    private class MyJavascriptInterface {
        @JavascriptInterface
        public void jumpForAndroid(String data) {
            Log.e("TAG", data);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.webview_right, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.webview_right_share:
                share();
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.webview_right_open:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(stringExtra));
                startActivity(intent);
                break;
            case R.id.webview_right_copy:
                ClipboardUtil.setText(this, stringExtra);
                Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //分享
    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, "来自「视界」的分享:" + stringExtra);
        startActivity(Intent.createChooser(intent, stringExtra));
    }

}


