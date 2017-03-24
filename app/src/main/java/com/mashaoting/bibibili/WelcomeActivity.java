package com.mashaoting.bibibili;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mashaoting.bibibili.utils.NetId;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class WelcomeActivity extends AppCompatActivity {


    @InjectView(R.id.tv_w)
    TextView tvW;
    @InjectView(R.id.iv_welcome)
    ImageView ivWelcome;
    @InjectView(R.id.activity_welcome)
    RelativeLayout activityWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.inject(this);
        Glide.with(this).load(NetId.WELCOME).into(ivWelcome);

        initTime();



    }

    private void initTime() {
        CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvW.setTextSize(20);
                tvW.setTextColor(Color.BLACK);
                tvW.setText("倒计时" + (int) millisUntilFinished / 1000);

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                finish();
            }
        }.start();
    }


}
