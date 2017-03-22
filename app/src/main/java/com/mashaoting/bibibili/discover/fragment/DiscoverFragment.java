package com.mashaoting.bibibili.discover.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by 麻少亭 on 2017/3/21.
 * <p>
 * 发现窗口
 */

public class DiscoverFragment extends BaseFragment {

    @InjectView(R.id.tv_sousu)
    TextView tvSousu;
    @InjectView(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    @InjectView(R.id.tv_more)
    TextView tvMore;
    @InjectView(R.id.tv_more2)
    TextView tvMore2;
    private FaXianBean faXianBean;
    private int widthPixels;
    private int heightPixels;
    private ViewGroup.LayoutParams layoutParams;

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

    boolean isok = false;

    @OnClick({R.id.tv_sousu, R.id.tv_more, R.id.tv_more2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sousu:
                break;
            case R.id.tv_more:
                isok = !isok;
                if(isok) {
                    initListener(faXianBean.getData().getList(), isok);
                    tvMore.setText("更多");
                }else {
                    tvMore.setText("收起");
                    initListener(faXianBean.getData().getList(), isok);
                }

                initListener(faXianBean.getData().getList(), isok);
//                tvMore.setVisibility(View.GONE);
//                tvMore2.setVisibility(View.INVISIBLE);
                Log.e("TAG", "DiscoverFragment onClick()");
                break;
            case R.id.tv_more2:
//                tvMore2.setVisibility(View.GONE);
//                tvMore.setVisibility(View.INVISIBLE);

                break;
        }
    }

    private void initListener(final List<FaXianBean.DataBean.ListBean> list, boolean isok) {
        int a = 1;
        if (isok) {
            a = 4;
        }
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < list.size() / a; i++) {
            stringList.add(list.get(i).getKeyword());
        }

        idFlowlayout.setAdapter(new TagAdapter<String>(stringList) {
            @Override
            public View getView(FlowLayout parent, final int position, String s) {
                TextView tv = new TextView(context);
                tv.setText(s);
                tv.setTextSize(10);
                tv.setBackgroundResource(R.drawable.tututu);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "被点击了" + list.get(position).getKeyword(), Toast.LENGTH_SHORT).show();
                    }
                });
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
                        Log.e("TAG", "DiscoverFragment onError()" + e + id);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        faXianBean = gson.fromJson(response, FaXianBean.class);
                        Log.e("TAG", "DiscoverFragment ----onResponse()" + response);
                        initListener(faXianBean.getData().getList(), true);
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
