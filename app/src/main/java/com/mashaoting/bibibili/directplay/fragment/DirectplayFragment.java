package com.mashaoting.bibibili.directplay.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.zhibobuju, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        londDatafromNet();
    }

    private void londDatafromNet() {


        OkHttpUtils
                .get()
                .url(NetId.ZHIBOZHU)
                .id(100)
                .build()
                .execute(new StringCallback() {

                    private DirectplayZBBean directplayZBBean;

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        directplayZBBean = gson.fromJson(response, DirectplayZBBean.class);

                        RecyLerViewAdapter adapter = new RecyLerViewAdapter(context, directplayZBBean.getData());
                        recylerview.setAdapter(adapter);


                        //布局管理器有问题  最左边充不满
//                        recylerview.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
                        recylerview.setLayoutManager(new GridLayoutManager(context, 1));

                    }


                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
