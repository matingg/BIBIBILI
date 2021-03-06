package com.mashaoting.bibibili;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.mashaoting.bibibili.adapter.LisViewAdapter;
import com.mashaoting.bibibili.adapter.MyViewPagerAdapter;
import com.mashaoting.bibibili.base.BaseoActivity;
import com.mashaoting.bibibili.directplay.fragment.DirectplayFragment;
import com.mashaoting.bibibili.discover.activity.TagFaXianActivity;
import com.mashaoting.bibibili.discover.bean.FaXianBean;
import com.mashaoting.bibibili.discover.bean.TagFaxianZonghe;
import com.mashaoting.bibibili.discover.fragment.DiscoverFragment;
import com.mashaoting.bibibili.discover.fragment.FragmentOne;
import com.mashaoting.bibibili.pursueplay.fragment.PursuePlayFragment;
import com.mashaoting.bibibili.recommend.fragment.RecommendFragment;
import com.mashaoting.bibibili.subarea.fragment.SubareaFragment;
import com.mashaoting.bibibili.utils.NetId;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

public class MainActivity extends BaseoActivity {

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
    @InjectView(R.id.iv_title)
    ImageView ivTitle;
    @InjectView(R.id.title_iv_default_avatar)
    ImageView titleIvDefaultAvatar;
    @InjectView(R.id.title_left_menu)
    LinearLayout titleLeftMenu;

    @InjectView(R.id.title_iv_right_menu)
    ImageView titleIvRightMenu;
    @InjectView(R.id.id_drawerlayout)
    DrawerLayout idDrawerlayout;

    @InjectView(R.id.rl_zuobuju)
    RelativeLayout rlZuobuju;
    @InjectView(R.id.lv_main_0)
    ListView lvMain0;
    @InjectView(R.id.title_iv_right_menu2)
    ImageView titleIvRightMenu2;
    private TagFaxianZonghe tagFaxianZonghe;


    @Override
    public int getLayoutid() {
        return R.layout.activity_main;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initData() {
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
        viewPagerAdapter.addFragment(new DiscoverFragment(), "发现");
        mainViewpager.setAdapter(viewPagerAdapter);//设置适配器
        mainViewpager.setOffscreenPageLimit(5);

    }

    @Override
    protected void initListener() {

        View view = View.inflate(this, R.layout.zuo_title, null);
        LisViewAdapter adapter = new LisViewAdapter(this);
        lvMain0.addHeaderView(view);
        lvMain0.setAdapter(adapter);
        lvMain0.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "被点击了" + parent.toString(), Toast.LENGTH_SHORT).show();
                idDrawerlayout.closeDrawers();
            }
        });


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }


    @OnClick({R.id.iv_title, R.id.title_left_menu, R.id.title_iv_right_menu2, R.id.title_iv_right_menu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title:
                idDrawerlayout.openDrawer(rlZuobuju);
                break;
            case R.id.title_left_menu:
                break;
            case R.id.title_iv_right_menu2:

                break;
            case R.id.title_iv_right_menu:
                SearchFragment searchFragment = SearchFragment.newInstance();
                searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
                    @Override
                    public void OnSearchClick(final String keyword) {
                        //这里处理逻辑
                        Toast.makeText(MainActivity.this, keyword, Toast.LENGTH_SHORT).show();
                        if (keyword != null){
                            Intent intent = new Intent(MainActivity.this , TagFaXianActivity.class);
                            intent.putExtra("id",keyword);
                            startActivity(intent);
                        }



                    }
                });
                searchFragment.show(getSupportFragmentManager(), SearchFragment.TAG);
                break;
        }
    }
}
