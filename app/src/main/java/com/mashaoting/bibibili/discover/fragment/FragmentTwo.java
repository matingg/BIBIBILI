package com.mashaoting.bibibili.discover.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.mashaoting.bibibili.base.BaseFragment;
import com.mashaoting.bibibili.discover.bean.TagFaxianZonghe;

/**
 * Created by 麻少亭 on 2017/3/27.
 */

public class FragmentTwo extends BaseFragment {
    TextView textView ;

    public FragmentTwo(TagFaxianZonghe tagFaxianZonghe) {

    }


    @Override
    public View initView() {
        textView = new TextView(context);
        textView.setText("aerngotbrj");
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
    @Override
    public void initData() {

    }
}
