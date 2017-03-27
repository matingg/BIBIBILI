package com.mashaoting.bibibili.discover.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.adapter.MyViewPagerAdapter;
import com.mashaoting.bibibili.discover.fragment.FaXianFragment;
import com.mashaoting.bibibili.discover.fragment.ShouYeFragment;
import com.mashaoting.bibibili.discover.fragment.WoDeFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class XingQuQuanActivity extends AppCompatActivity {

    @InjectView(R.id.toolBar)
    Toolbar toolBar;
    @InjectView(R.id.tabLayout)
    TabLayout tabLayout;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;
    @InjectView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xing_qu_quan);
        ButterKnife.inject(this);


        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolBar);
        mToolbar.setTitleTextColor(Color.WHITE);//设置ToolBar的titl颜色
        setSupportActionBar(mToolbar);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new ShouYeFragment(), "首页");//添加Fragment
        viewPagerAdapter.addFragment(new FaXianFragment(), "发现");
        viewPagerAdapter.addFragment(new WoDeFragment(), "我的");
        mViewPager.setAdapter(viewPagerAdapter);//设置适配器

        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.addTab(mTabLayout.newTab().setText("首页"));//给TabLayout添加Tab
        mTabLayout.addTab(mTabLayout.newTab().setText("发现"));
        mTabLayout.addTab(mTabLayout.newTab().setText("我的"));
        mTabLayout.setupWithViewPager(mViewPager);//给TabLayout设置关联ViewPager，如果设置了ViewPager，那么ViewPagerAdapter中的getPageTitle()方法返回的就是Tab上的标题



    }
}
