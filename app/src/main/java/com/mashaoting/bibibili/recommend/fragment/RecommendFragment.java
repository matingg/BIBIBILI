package com.mashaoting.bibibili.recommend.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.base.BaseFragment;
import com.mashaoting.bibibili.recommend.adapter.VptabAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.mashaoting.bibibili.R.id.tab_FindFragment_title;
import static com.mashaoting.bibibili.R.id.vp_FindFragment_pager;

/**
 * Created by 麻少亭 on 2017/3/21.
 * <p>
 * 推荐窗口
 */

public class RecommendFragment extends BaseFragment {

    @InjectView(tab_FindFragment_title)
    TabLayout tabFindFragmentTitle;
    @InjectView(vp_FindFragment_pager)
    ViewPager vpFindFragmentPager;

    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;                                     //tab名称列表

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.tuijian_fragment, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        list_fragment = new ArrayList<>();
        list_title = new ArrayList<>();
        list_fragment.add(new ZongHeFragment());
        list_fragment.add(new DongTaiFragment());
        list_title.add("综合");
        list_title.add("动态");
        tabFindFragmentTitle.setTabMode(TabLayout.MODE_SCROLLABLE);//设置模式
        VptabAdapter adapter =new VptabAdapter(getChildFragmentManager() , list_fragment , list_title);
        vpFindFragmentPager.setAdapter(adapter);
        //TabLayout加载viewpager
        tabFindFragmentTitle.setupWithViewPager(vpFindFragmentPager);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
