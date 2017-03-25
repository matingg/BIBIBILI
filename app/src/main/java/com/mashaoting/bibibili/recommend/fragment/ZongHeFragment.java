package com.mashaoting.bibibili.recommend.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.base.BaseFragment;
import com.mashaoting.bibibili.directplay.bean.DirectplayZBBean;
import com.mashaoting.bibibili.recommend.adapter.ZHGridViewAdapter;
import com.mashaoting.bibibili.recommend.bean.ZongHeBean;
import com.mashaoting.bibibili.utils.NetId;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 麻少亭 on 2017/3/23.
 * 推荐       之      综合页面
 */

public class ZongHeFragment extends BaseFragment {

    @InjectView(R.id.gv_zonghe)
    GridView gvZonghe;
    @InjectView(R.id.swipe_refresh_widget)
    SwipeRefreshLayout swipeRefreshWidget;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.zonghe, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {

        loadNetFromNet();
        initListener();

    }

    private void initListener() {

        swipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

//                true是加载更多 还是刷新
                if (true) {
                    loadNetFromNet();
                    Toast.makeText(context, "下拉刷新  onRefresh()", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "上拉加载", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void loadNetFromNet() {
        OkHttpUtils
                .get()
                .url(NetId.TUIJIAN)
                .id(100)
                .build()
                .execute(new StringCallback() {

                    private DirectplayZBBean directplayZBBean;

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        swipeRefreshWidget.setRefreshing(false);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ZongHeBean zongHeBean = JSON.parseObject(response, ZongHeBean.class);

                        ZHGridViewAdapter adapter = new ZHGridViewAdapter(context, zongHeBean.getData());

                        gvZonghe.setAdapter(adapter);
                        swipeRefreshWidget.setRefreshing(false);
                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }
}