package com.mashaoting.bibibili.discover.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.base.BaseFragment;
import com.mashaoting.bibibili.discover.bean.FaXianBean;
import com.mashaoting.bibibili.utils.NetId;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 麻少亭 on 2017/3/21.
 * <p>
 * 发现窗口
 */

public class DiscoverFragment extends BaseFragment {

    @InjectView(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    private FaXianBean faXianBean;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.faxian, null);
        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void initData() {
        getDataFromNet();


    }

    private void initListener(List<FaXianBean.DataBean.ListBean> list) {

        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            stringList.add(list.get(i).getKeyword());
        }

        idFlowlayout.setAdapter(new TagAdapter<String>(stringList)
        {
            @Override
            public View getView(FlowLayout parent, int position, String s)
            {
                TextView tv = new TextView(context);
                tv.setText(s);
                tv.setBackgroundResource(R.drawable.tututu);
                return tv;
            }
        });

    }

    private void getDataFromNet() {

        OkHttpUtils
                .get()
                .url(NetId.FAXIAN)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "DiscoverFragment onError()"+e +id);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        faXianBean = gson.fromJson(response, FaXianBean.class);
                        Log.e("TAG", "DiscoverFragment ----onResponse()"+response);
                        initListener(faXianBean.getData().getList());
                    }
                });


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
