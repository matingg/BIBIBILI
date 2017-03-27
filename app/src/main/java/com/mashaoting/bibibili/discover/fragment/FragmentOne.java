package com.mashaoting.bibibili.discover.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.base.BaseFragment;
import com.mashaoting.bibibili.discover.adapter.GridAdapter;
import com.mashaoting.bibibili.discover.bean.TagFaxianZonghe;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by 麻少亭 on 2017/3/27.
 */

public class FragmentOne extends BaseFragment {
    private final TagFaxianZonghe tagFaxianZonghe;
    @InjectView(R.id.paixu)
    TextView paixu;
    @InjectView(R.id.shichang)
    TextView shichang;
    @InjectView(R.id.fenqu)
    TextView fenqu;
    @InjectView(R.id.gridview_one)
    GridView gridviewOne;

    public FragmentOne(TagFaxianZonghe tagFaxianZonghe) {
        this.tagFaxianZonghe = tagFaxianZonghe;
    }


    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.fragment_one, null);
        return view;
    }

    @Override
    public void initData() {


        GridAdapter adapter = new GridAdapter(context ,tagFaxianZonghe);
        gridviewOne.setAdapter(adapter);

    }

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

    @OnClick({R.id.paixu, R.id.shichang, R.id.fenqu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.paixu:
                toast("morenpaixu");
                break;
            case R.id.shichang:
                Toast.makeText(context, "全部时长", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fenqu:
                Toast.makeText(context, "全部分区", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
