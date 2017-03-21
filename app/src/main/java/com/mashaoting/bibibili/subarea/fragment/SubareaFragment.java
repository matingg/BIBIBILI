package com.mashaoting.bibibili.subarea.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.mashaoting.bibibili.base.BaseFragment;

/**
 * Created by 麻少亭 on 2017/3/21.
 *
 *    分区窗口
 *
 */

public class SubareaFragment extends BaseFragment {
    TextView textView;  //必须在下面 new 否则 空指针
    @Override
    public View initView() {
        textView = new TextView(context);
        textView.setTextColor(Color.BLUE);
        textView.setTextSize(32);
        return textView;
    }

    @Override
    public void initData() {
        textView.setText("分区窗口");
    }
}
