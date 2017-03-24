package com.mashaoting.bibibili.pursueplay.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

                        ZHRecyclerViewAdapter adapter = new ZHRecyclerViewAdapter(context , result);
                        myRecyclerView.setAdapter(adapter);

                        GridLayoutManager gridLayoutManager =  new GridLayoutManager(context,3);
                        myRecyclerView.setLayoutManager(gridLayoutManager);
                        //设置布局管理器    当position == 1 时  返回 三个格子  否则 占 一个
                        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){

                            @Override
                            public int getSpanSize(int position) {
                                if (position == 1) {
                                    return 3;
                                }
                                    return 1 ;
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
}
