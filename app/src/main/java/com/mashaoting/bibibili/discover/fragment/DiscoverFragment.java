package com.mashaoting.bibibili.discover.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mashaoting.bibibili.MainActivity;
import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.base.BaseFragment;
import com.mashaoting.bibibili.discover.activity.XingQuQuanActivity;
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
    @InjectView(R.id.id_flowlayout1)
    TagFlowLayout idFlowlayout1;
    @InjectView(R.id.nestedscrllview)
    NestedScrollView nestedscrllview;
    @InjectView(R.id.xingququan0)
    TextView xingququan0;
    @InjectView(R.id.huati1)
    TextView huati1;
    @InjectView(R.id.huodong2)
    TextView huodong2;
    @InjectView(R.id.xiaoheiwu)
    TextView xiaoheiwu;
    @InjectView(R.id.yuanchuang)
    TextView yuanchuang;
    @InjectView(R.id.quanqu)
    TextView quanqu;
    private FaXianBean faXianBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

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


    private void initListener1(final List<FaXianBean.DataBean.ListBean> list) {

        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            stringList.add(list.get(i).getKeyword());
        }

        idFlowlayout1.setAdapter(new TagAdapter<String>(stringList) {
            @Override
            public View getView(FlowLayout parent, final int position, String s) {
                TextView tv = new TextView(context);
                tv.setText(s);
                tv.setTextSize(20);

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


    private void initListener(final List<FaXianBean.DataBean.ListBean> list) {

        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            stringList.add(list.get(i).getKeyword());
        }

        idFlowlayout.setAdapter(new TagAdapter<String>(stringList) {
            @Override
            public View getView(FlowLayout parent, final int position, String s) {
                TextView tv = new TextView(context);
                tv.setText(s);
                tv.setTextSize(20);

                tv.setBackgroundResource(R.drawable.tututu);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "被点击了" + list.get(position).getKeyword(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, MainActivity.class);

                        startActivity(intent);
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
                        initListener1(faXianBean.getData().getList());
                    }
                });


    }


    @OnClick({R.id.tv_sousu, R.id.tv_more, R.id.tv_more2, R.id.xingququan0, R.id.huati1, R.id.huodong2, R.id.xiaoheiwu, R.id.yuanchuang, R.id.quanqu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sousu:
                break;
            case R.id.tv_more:
                idFlowlayout1.setVisibility(View.GONE);
                nestedscrllview.setVisibility(View.VISIBLE);
                tvMore.setVisibility(View.GONE);
                tvMore2.setVisibility(View.VISIBLE);
                if (faXianBean != null) {
                    initListener(faXianBean.getData().getList());
                }

                break;
            case R.id.tv_more2:
                idFlowlayout1.setVisibility(View.VISIBLE);
                nestedscrllview.setVisibility(View.GONE);
                tvMore2.setVisibility(View.GONE);
                tvMore.setVisibility(View.VISIBLE);
                if (faXianBean != null) {
                    initListener(faXianBean.getData().getList());
                }
                    break ;
            case R.id.xingququan0:
                Intent intent = new Intent(getActivity() , XingQuQuanActivity.class);
                startActivity(intent);
                Toast.makeText(context, "兴趣圈", Toast.LENGTH_SHORT).show();
                break;
            case R.id.huati1:
                Toast.makeText(context, "话题", Toast.LENGTH_SHORT).show();
                break;
            case R.id.huodong2:
                Toast.makeText(context, "活动", Toast.LENGTH_SHORT).show();
                break;
            case R.id.xiaoheiwu:
                Toast.makeText(context, "小黑屋", Toast.LENGTH_SHORT).show();
                break;
            case R.id.yuanchuang:
                Toast.makeText(context, "原创", Toast.LENGTH_SHORT).show();
                break;
            case R.id.quanqu:
                Toast.makeText(context, "全区", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
