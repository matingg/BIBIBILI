package com.mashaoting.bibibili.discover.fragment;

import android.view.View;
import android.widget.TextView;

import com.mashaoting.bibibili.base.BaseFragment;

/**
 * Created by 麻少亭 on 2017/3/26.
 */

public class ShouYeFragment extends BaseFragment {
    TextView textView;

    @Override
    public View initView() {
        textView = new TextView(context);
        textView.setText("srgrgg");
        return textView;
    }

    @Override
    public void initData() {

    }
}
