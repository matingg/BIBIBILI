package com.mashaoting.bibibili.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;


/**
 * Created by 麻少亭 on 2017/3/20.
 */


public abstract class BaseoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutid());
        ButterKnife.inject(this);
        initTitle();
        initData();
        initListener();
    }

    public abstract int getLayoutid();

    protected abstract void initTitle();

    protected abstract void initData();

    protected abstract void initListener();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.inject(this);
    }

    //弹出吐司
    public void showToast(String context) {
        Toast.makeText(this, context, Toast.LENGTH_SHORT).show();
    }
}

