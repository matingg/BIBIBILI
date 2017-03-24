package com.mashaoting.bibibili.pursueplay.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.base.BaseFragment;
import com.mashaoting.bibibili.directplay.bean.DirectplayZBBean;
import com.mashaoting.bibibili.pursueplay.adapter.ZHRecyclerViewAdapter;
import com.mashaoting.bibibili.pursueplay.bean.ZhuiFanBean;
import com.mashaoting.bibibili.utils.NetId;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 麻少亭 on 2017/3/21.
 * <p>
 * 追番窗口
 */

public class PursuePlayFragment extends BaseFragment {


    @InjectView(R.id.my_recycler_view)
    RecyclerView myRecyclerView;
    @InjectView(R.id.rl_fanju)
    RelativeLayout rlFanju;
    @InjectView(R.id.rl_guoju)
    RelativeLayout rlGuoju;
    @InjectView(R.id.tv_time)
    TextView tvTime;
    @InjectView(R.id.tv_suoyin)
    TextView tvSuoyin;
    @InjectView(R.id.look_tuijian)
    ImageView lookTuijian;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.zhuifan, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {

        getDatafromNet();

    }

    private void getDatafromNet() {
        OkHttpUtils
                .get()
                .url(NetId.ZHUIFAN)
                .id(100)
                .build()
                .execute(new StringCallback() {

                    private DirectplayZBBean directplayZBBean;

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        ZhuiFanBean zhuiFanBean = JSON.parseObject(response, ZhuiFanBean.class);
                        ZhuiFanBean.ResultBean result = zhuiFanBean.getResult();

                        ZHRecyclerViewAdapter adapter = new ZHRecyclerViewAdapter(context, result);
                        myRecyclerView.setAdapter(adapter);

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
                        myRecyclerView.setLayoutManager(gridLayoutManager);
//                        设置布局管理器    当position == 1 时  返回 三个格子  否则 占 一个
                        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

                            @Override
                            public int getSpanSize(int position) {
                                if (position == 4) {
                                    return 3;
                                }
                                return 1;
                            }
                        });


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
