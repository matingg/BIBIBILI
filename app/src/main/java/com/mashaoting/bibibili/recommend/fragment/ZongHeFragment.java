package com.mashaoting.bibibili.recommend.fragment;

import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.alibaba.fastjson.JSON;
import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.base.BaseFragment;
import com.mashaoting.bibibili.directplay.bean.DirectplayZBBean;
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

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.zonghe, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        loadNetFromNet();


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

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ZongHeBean zongHeBean = JSON.parseObject(response, ZongHeBean.class);

                        Log.e("TAG", "ZongHeFragment onResponse()"
                        +zongHeBean.getData().get(0).getTitle());
                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}