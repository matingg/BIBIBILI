package com.mashaoting.bibibili.subarea.fragment;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.base.BaseFragment;
import com.mashaoting.bibibili.subarea.adapter.FenQuRecyclerAdapter;
import com.mashaoting.bibibili.subarea.bean.FenQuBean;
import com.mashaoting.bibibili.utils.NetId;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 麻少亭 on 2017/3/21.
 * <p>
 * 分区窗口
 */

public class SubareaFragment extends BaseFragment {

    @InjectView(R.id.my_recycler_view)
    RecyclerView myRecyclerView;
    @InjectView(R.id.swipe_refresh_widget)
    SwipeRefreshLayout swipeRefreshWidget;
    private FenQuBean fenQuBean;

    private List<FenQuBean.DataBean> data;
    private FenQuRecyclerAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fenqu_fragment, null);
        ButterKnife.inject(this, view);
        initListener();

        return view;
    }

    private void initListener() {

        swipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

//                true是加载更多 还是刷新
                if (true) {
                    loadneti();
                    Toast.makeText(context, "下拉刷新  onRefresh()", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "上拉加载", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void initData() {


        loadneti();

    }

    private void loadneti() {
        OkHttpUtils
                .get()
                .url(NetId.FENQU)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        swipeRefreshWidget.setRefreshing(false);
                        Toast.makeText(context, "加载失败v", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        fenQuBean = JSON.parseObject(response, FenQuBean.class);

                        if(adapter!=null) {
                            adapter.refreshAdapter(fenQuBean.getData());
                            swipeRefreshWidget.setRefreshing(false);
                        }else {
                            adapter = new FenQuRecyclerAdapter(context,fenQuBean.getData());
                            myRecyclerView.setAdapter(adapter);
                            myRecyclerView.setLayoutManager(new LinearLayoutManager(context, 1, false));
                            swipeRefreshWidget.setRefreshing(false);
                        }

                    }

                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


}
