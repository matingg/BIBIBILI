package com.mashaoting.bibibili.subarea.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.base.BaseFragment;
import com.mashaoting.bibibili.directplay.bean.DirectplayZBBean;
import com.mashaoting.bibibili.subarea.adapter.FenQuRecyclerAdapter;
import com.mashaoting.bibibili.subarea.bean.FenQuBean;
import com.mashaoting.bibibili.utils.NetId;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

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

    private FenQuBean fenQuBean;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fenqu_fragment, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {

        OkHttpUtils
                .get()
                .url(NetId.FENQU)
                .id(100)
                .build()
                .execute(new StringCallback() {

                    private DirectplayZBBean directplayZBBean;

                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        fenQuBean = JSON.parseObject(response, FenQuBean.class);

                        FenQuRecyclerAdapter adapter = new FenQuRecyclerAdapter(context, fenQuBean.getData().getNewX());
                        myRecyclerView.setAdapter(adapter);
                        GridLayoutManager gridLayoutManager =  new GridLayoutManager(context,1);
                        myRecyclerView.setLayoutManager(gridLayoutManager);
//                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context , LinearLayoutManager.VERTICAL ,false);
//                        myRecyclerView.setLayoutManager(linearLayoutManager);
                    }

                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


}
