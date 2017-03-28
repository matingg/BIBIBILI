package com.mashaoting.bibibili.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mashaoting.bibibili.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TextActivity extends AppCompatActivity {

    @InjectView(R.id.toolBar)
    Toolbar toolBar;
    @InjectView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @InjectView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        ButterKnife.inject(this);

    }
}
