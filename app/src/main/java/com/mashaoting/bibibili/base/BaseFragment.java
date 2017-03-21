package com.mashaoting.bibibili.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 麻少亭 on 2017/3/20.
 */

public abstract class BaseFragment extends Fragment {
    public Context context;

    /**
     * 当Fragment被创建的时候回调
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();//MainActivity
    }


    /**
     * 当创建时候的时候回调
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    /**
     * 有孩子强制实现
     *
     * @return
     */
    public abstract View initView();

    /**
     * 当Activtiy创建好的时候回调
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 联网请求数据
     * 视图需要绑定数据的时候，子类重写该方法
     */
    public abstract void initData();
}
