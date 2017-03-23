package com.mashaoting.bibibili.recommend.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.mashaoting.bibibili.base.BaseFragment;

/**
 * Created by 麻少亭 on 2017/3/23.
 *
 *            推荐  之 动态页面
 */

public class DongTaiFragment extends BaseFragment {
    TextView textView ;
    @Override
    public View initView() {
        textView = new TextView(context);

        textView.setTextSize(35);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        textView.setText("动态页面");
    }
}
