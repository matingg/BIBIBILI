package com.mashaoting.bibibili.shipin;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.directplay.bean.Beann;
import com.shuyu.gsyvideoplayer.GSYPreViewManager;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DanmkuVideoActivity extends AppCompatActivity {

    @InjectView(R.id.post_detail_nested_scroll)
    NestedScrollView postDetailNestedScroll;
    @InjectView(R.id.danmaku_player)
    DanmakuVideoPlayer danmakuPlayer;
    @InjectView(R.id.activity_detail_player)
    RelativeLayout activityDetailPlayer;
    @InjectView(R.id.webview_0)
    WebView webview;
    private boolean isPlay;
    private boolean isPause;
    private OrientationUtils orientationUtils;
    private String url;
    private String playname;
    //    ImageView imageView = new ImageView(this);
    private Beann baann;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danmku_video);
        ButterKnife.inject(this);
//        url = getIntent().getStringExtra("b");
        baann = new Beann();
        baann = (Beann) getIntent().getSerializableExtra("src");
        url = baann.getPlayurl();
        playname = baann.getTitle();
//        Glide.with(this).load(baann.getFace()).into(imageView);
        //使用自定义的全屏切换图片，!!!注意xml布局中也需要设置为一样的
        //必须在setUp之前设置
        danmakuPlayer.setShrinkImageRes(R.drawable.custom_shrink);
        danmakuPlayer.setEnlargeImageRes(R.drawable.custom_enlarge);
        initData();

        if (url != null) {
            danmakuPlayer.setUp(url, true, null, playname);
        }


        //增加封面
        ImageView imageView = new ImageView(this);
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setImageResource(R.mipmap.xxx1);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(this).load(baann.getFace()).into(imageView);
        danmakuPlayer.setThumbImageView(imageView);

        resolveNormalVideoUI();

        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, danmakuPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);

        danmakuPlayer.setIsTouchWiget(true);
        //关闭自动旋转
        danmakuPlayer.setRotateViewAuto(false);
        danmakuPlayer.setLockLand(false);
        danmakuPlayer.setShowFullAnimation(false);
        danmakuPlayer.setNeedLockFull(true);

        //detailPlayer.setOpenPreView(true);
        danmakuPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();

                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                danmakuPlayer.startWindowFullscreen(DanmkuVideoActivity.this, true, true);
            }
        });

        danmakuPlayer.setStandardVideoAllCallBack(new SampleListener() {
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                //开始播放了才能旋转和全屏
                orientationUtils.setEnable(true);
                isPlay = true;
            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
                super.onAutoComplete(url, objects);
            }

            @Override
            public void onClickStartError(String url, Object... objects) {
                super.onClickStartError(url, objects);
            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);
                if (orientationUtils != null) {
                    orientationUtils.backToProtVideo();
                }
            }
        });

        danmakuPlayer.setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils.setEnable(!lock);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {

        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }

        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }


    @Override
    protected void onPause() {
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoPlayer.releaseAllVideos();
        GSYPreViewManager.instance().releaseMediaPlayer();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
                if (!danmakuPlayer.isIfCurrentIsFullscreen()) {
                    danmakuPlayer.startWindowFullscreen(DanmkuVideoActivity.this, true, true);
                }
            } else {
                //新版本isIfCurrentIsFullscreen的标志位内部提前设置了，所以不会和手动点击冲突
                if (danmakuPlayer.isIfCurrentIsFullscreen()) {
                    StandardGSYVideoPlayer.backFromWindowFull(this);
                }
                if (orientationUtils != null) {
                    orientationUtils.setEnable(true);
                }
            }
        }
    }


    private void resolveNormalVideoUI() {
        //增加title
        danmakuPlayer.getTitleTextView().setVisibility(View.GONE);
        danmakuPlayer.getTitleTextView().setText("测试视频");
        danmakuPlayer.getBackButton().setVisibility(View.GONE);
    }


    protected void initData() {
        //设置WebView属性，能够执行Javascript脚本
        webview.getSettings().setJavaScriptEnabled(true);
        //加载需要显示的网页
        webview.loadUrl("http://neihanshequ.com/");
        //设置Web视图
        webview.setWebViewClient(new HelloWebViewClient ());
    }

//    @Override
//    //设置回退
//    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
//            webview.goBack(); //goBack()表示返回WebView的上一页面
//            return true;
//        }
//        return false;
//    }

    //Web视图
    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}


