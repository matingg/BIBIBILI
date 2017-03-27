package com.mashaoting.bibibili.discover.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
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
import java.util.Set;

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
    private List<String> stringList;
    private LayoutInflater mInflater;

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


    private void initListener(final List<FaXianBean.DataBean.ListBean> list) {
        stringList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            stringList.add(list.get(i).getKeyword());
        }


    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mInflater = LayoutInflater.from(getActivity());

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
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        faXianBean = gson.fromJson(response, FaXianBean.class);


                        if(faXianBean != null) {
                            initText1();
                            inittext2();
                        }

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
                initText1();
                break;


            case R.id.tv_more2:

                idFlowlayout1.setVisibility(View.VISIBLE);
                nestedscrllview.setVisibility(View.GONE);
                tvMore2.setVisibility(View.GONE);
                tvMore.setVisibility(View.VISIBLE);
                inittext2();
                break;

            case R.id.xingququan0:
                Intent intent = new Intent(getActivity(), XingQuQuanActivity.class);
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
















    private void inittext2() {
        idFlowlayout.setAdapter(new TagAdapter<String>(stringList) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        idFlowlayout1, false);
                tv.setText(s);
                return tv;
            }

            @Override
            public boolean setSelected(int position, String s) {
                return s.equals("Android");
            }
        });

        idFlowlayout1.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Toast.makeText(getActivity(), stringList.get(position), Toast.LENGTH_SHORT).show();
                //view.setVisibility(View.GONE);
                return true;
            }
        });


        idFlowlayout1.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                getActivity().setTitle("choose:" + selectPosSet.toString());
            }
        });
    }


    private void initText1() {

        initListener(faXianBean.getData().getList());
        idFlowlayout1.setAdapter(new TagAdapter<String>(stringList) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        idFlowlayout1, false);
                tv.setText(s);
                return tv;
            }

            @Override
            public boolean setSelected(int position, String s) {
                return s.equals("Android");
            }
        });

        idFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Toast.makeText(getActivity(), stringList.get(position), Toast.LENGTH_SHORT).show();
//                            view.setVisibility(View.GONE);
                return true;
            }
        });

    }
}
