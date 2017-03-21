package com.mashaoting.bibibili.recommend.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.mashaoting.bibibili.base.BaseFragment;

/**
 * Created by 麻少亭 on 2017/3/21.
 *
 *    推荐窗口
 *
 */

public class RecommendFragment extends BaseFragment {
    TextView textView ;
    @Override
    public View initView() {
        textView = new TextView(context);
        textView.setTextColor(Color.BLUE);
        textView.setTextSize(32);
        return textView;
    }

    @Override
    public void initData() {
        textView.setText("推荐窗口");
    }
}
