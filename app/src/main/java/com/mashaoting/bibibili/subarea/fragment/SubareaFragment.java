package com.mashaoting.bibibili.subarea.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private FenQuBean fenQuBean;

    private List<FenQuBean.DataBean> data;

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
                        fenQuBean = JSON.parseObject(response,FenQuBean.class);
                        data = fenQuBean.getData();
                        Log.e("TAG", "S4444444444444444444444444444"+data.get(0).getBody().get(0).getTitle());
                        Log.e("TAG", "S4444444444444444444444444444"+data.get(0).getTitle());
                        FenQuRecyclerAdapter adapter = new FenQuRecyclerAdapter(context , data);
                        myRecyclerView.setAdapter(adapter);
                        myRecyclerView.setLayoutManager(new LinearLayoutManager(context , 1 ,false));
                    }

                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


}
