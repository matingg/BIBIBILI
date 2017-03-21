package com.mashaoting.bibibili;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.mashaoting.bibibili.adapter.MyViewPagerAdapter;
import com.mashaoting.bibibili.directplay.fragment.DirectplayFragment;
import com.mashaoting.bibibili.pursueplay.fragment.PursuePlayFragment;
import com.mashaoting.bibibili.recommend.fragment.RecommendFragment;
import com.mashaoting.bibibili.subarea.fragment.SubareaFragment;
import com.mashaoting.bibibili.utils.DensityUtil;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends SlidingFragmentActivity {

    @InjectView(R.id.toolBar)
    Toolbar toolBar;
    @InjectView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @InjectView(R.id.tabLayout)
    TabLayout tabLayout;
    @InjectView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @InjectView(R.id.main_viewpager)
    ViewPager mainViewpager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//设置主页面
        ButterKnife.inject(this);

        setBehindContentView(R.layout.left); //设置左侧菜单

        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setSecondaryMenu(R.layout.left);
        slidingMenu.setMode(SlidingMenu.LEFT); //设置模式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// 设置触摸屏幕的模式
        // 设置滑动菜单视图的宽度
        slidingMenu.setBehindOffset(DensityUtil.px2dip(this, 100));


        tabLayout.addTab(tabLayout.newTab().setText("直播"));
        tabLayout.addTab(tabLayout.newTab().setText("推荐"));
        tabLayout.addTab(tabLayout.newTab().setText("追番"));
        tabLayout.addTab(tabLayout.newTab().setText("分区"));
        tabLayout.addTab(tabLayout.newTab().setText("发现"));
        tabLayout.setupWithViewPager(mainViewpager);

        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new DirectplayFragment(), "直播");//添加Fragment
        viewPagerAdapter.addFragment(new RecommendFragment(), "推荐");
        viewPagerAdapter.addFragment(new PursuePlayFragment(), "追番");
        viewPagerAdapter.addFragment(new SubareaFragment(), "分区");
        viewPagerAdapter.addFragment(new DirectplayFragment(), "发现");
        mainViewpager.setAdapter(viewPagerAdapter);//设置适配器


//        Toolbar toolbar = new Toolbar(this);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("这里是Title");
//        toolbar.setSubtitle("这里是子标题");
//        toolbar.setLogo(R.drawable.icon);
//        setSupportActionBar(toolbar);


    }


}
