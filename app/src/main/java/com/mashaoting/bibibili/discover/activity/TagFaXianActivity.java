package com.mashaoting.bibibili.discover.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.adapter.MyViewPagerAdapter;
import com.mashaoting.bibibili.base.BaseoActivity;
import com.mashaoting.bibibili.discover.bean.TagFaxianZonghe;
import com.mashaoting.bibibili.discover.fragment.FragmentFour;
import com.mashaoting.bibibili.discover.fragment.FragmentOne;
import com.mashaoting.bibibili.discover.fragment.FragmentThree;
import com.mashaoting.bibibili.discover.fragment.FragmentTwo;
import com.mashaoting.bibibili.utils.NetId;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

public class TagFaXianActivity extends BaseoActivity {

    @InjectView(R.id.toolBar)
    Toolbar toolBar;
    @InjectView(R.id.tabLayout)
    TabLayout tabLayout;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;
    @InjectView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    private TagFaxianZonghe tagFaxianZonghe;
    private String stringExtra;

    @Override
    public int getLayoutid() {
        Intent intent = getIntent();
        stringExtra = intent.getStringExtra("id");
        return R.layout.activity_tag_fa_xian;
    }

    @Override
    protected void initTitle() {
        initdatafromNet();

    }

    @Override
    protected void initData() {


    }


    @Override
    protected void initListener() {

    }


    private void initdatafromNet() {
        OkHttpUtils
                .get()
                .url(NetId.FAXIANZHIZONGHE1 + stringExtra + NetId.FAXIANZHIZONGHE2)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        tagFaxianZonghe = JSON.parseObject(response, TagFaxianZonghe.class);
                        if (tagFaxianZonghe.getData() != null) {
                            initTitleBar(tagFaxianZonghe.getData());
                        }
                    }
                });

    }


    private void initTitleBar(TagFaxianZonghe.DataBean data) {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolBar);
        mToolbar.setTitleTextColor(Color.WHITE);//设置ToolBar的titl颜色
        setSupportActionBar(mToolbar);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.addTab(mTabLayout.newTab());//给TabLayout添加Tab
        for (int i = 0; i < data.getNav().size(); i++) {
            if (i == 0 && data.getNav().get(i).getName() != null) {
                viewPagerAdapter.addFragment(new FragmentOne(tagFaxianZonghe), data.getNav().get(i).getName());//添加Fragment
                mTabLayout.addTab(mTabLayout.newTab().setText(data.getNav().get(i).getName()));
            } else if (i == 1 && data.getNav().get(i).getName() != null) {
                viewPagerAdapter.addFragment(new FragmentTwo(tagFaxianZonghe), data.getNav().get(i).getName());
                mTabLayout.addTab(mTabLayout.newTab().setText(data.getNav().get(i).getName()));
            } else if (i == 2 && data.getNav().get(i).getName() != null) {
                viewPagerAdapter.addFragment(new FragmentThree(tagFaxianZonghe), data.getNav().get(i).getName());
                mTabLayout.addTab(mTabLayout.newTab().setText(data.getNav().get(i).getName()));
            } else if (i == 3 && data.getNav().get(i).getName() != null) {
                viewPagerAdapter.addFragment(new FragmentFour(tagFaxianZonghe), data.getNav().get(i).getName());
                mTabLayout.addTab(mTabLayout.newTab().setText(data.getNav().get(i).getName()));
            }
        }
        mViewPager.setAdapter(viewPagerAdapter);//设置适配器
        mTabLayout.setTabMode(MODE_APPEND);
        mTabLayout.setupWithViewPager(mViewPager);//给TabLayout设置关联ViewPager，如果设置了ViewPager，那么ViewPagerAdapter中的getPageTitle()方法返回的就是Tab上的标题
    }
//  --parallel --offline
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
