package com.mashaoting.bibibili.directplay.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.base.BaseFragment;
import com.mashaoting.bibibili.directplay.adapter.RecyLerViewAdapter;
import com.mashaoting.bibibili.directplay.bean.DirectplayZBBean;
import com.mashaoting.bibibili.utils.NetId;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 麻少亭 on 2017/3/21.
 * <p>
 * 直播窗口
 */

public class DirectplayFragment extends BaseFragment {


    @InjectView(R.id.recylerview)
    RecyclerView recylerview;
    @InjectView(R.id.demo_swiperefreshlayout)
    SwipeRefreshLayout demoSwiperefreshlayout;
    @InjectView(R.id.cardView)
    CardView cardView;
    private RecyLerViewAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.zhibobuju, null);
        ButterKnife.inject(this, view);
        cardView.setRadius(20);//设置图片圆角的半径大小

        cardView.setCardElevation(20);//设置阴影部分大小

        cardView.setContentPadding(15,15,15,15);//设置图片距离阴影大小
        return view;
    }

    @Override
    public void initData() {
        londDatafromNet();
        initLIstenner();
    }

    private void initLIstenner() {

        demoSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

//                true是加载更多 还是刷新
                if (true) {
                    londDatafromNet();
                    Toast.makeText(context, "下拉刷新  onRefresh()", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "上拉加载", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void londDatafromNet() {


        OkHttpUtils
                .get()
                .url(NetId.ZHIBOZHU)
                .id(100)
                .build()
                .execute(new StringCallback() {

                    private DirectplayZBBean directplayZBBean;

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        demoSwiperefreshlayout.setRefreshing(false);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        directplayZBBean = gson.fromJson(response, DirectplayZBBean.class);

                        if (adapter == null) {
                            adapter = new RecyLerViewAdapter(context, directplayZBBean.getData());
                            recylerview.setAdapter(adapter);
                            recylerview.setLayoutManager(new GridLayoutManager(context, 1));

                            demoSwiperefreshlayout.setRefreshing(false);
                        } else {
                            adapter.refreshAdapter(directplayZBBean.getData());
                            demoSwiperefreshlayout.setRefreshing(false);
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
