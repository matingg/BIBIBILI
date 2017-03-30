package com.mashaoting.bibibili.subarea;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.anye.greendao.gen.JavasBeanDao;
import com.bumptech.glide.Glide;
import com.mashaoting.bibibili.MyApplication;
import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.subarea.bean.JavasBean;
import com.mashaoting.bibibili.subarea.jiajianhao.GouWuCheActivity;
import com.mashaoting.bibibili.subarea.jiajianhao.IncreaseReduceTextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class TestActivity extends AppCompatActivity {


    @InjectView(R.id.image_tupian)
    ImageView imageTupian;
    @InjectView(R.id.text_biao)
    TextView textBiao;
    @InjectView(R.id.text_jiage)
    TextView textJiage;
    @InjectView(R.id.jisuan)
    IncreaseReduceTextView jisuan;
    @InjectView(R.id.queding)
    Button queding;
    @InjectView(R.id.gouwuche)
    Button gouwuche;
    private JavasBeanDao mUserDao;
    private JavasBean javasBean;
    private List<JavasBean> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mUserDao = MyApplication.getInstances().getDaoSession().getJavasBeanDao();
        ButterKnife.inject(this);
        javasBean = (JavasBean) getIntent().getSerializableExtra("ui");
        textBiao.setText(javasBean.getTou());
        textJiage.setText("价格：" + javasBean.getDanmuku());
        Glide.with(this).load(javasBean.getTuid()).into(imageTupian);
        initJiaGe();


    }

    int number1 = 1;

    private void initJiaGe() {


        jisuan.setOnNumberChangeListener(new IncreaseReduceTextView.OnNumberChangeListener() {
            @Override
            public void onNumberChange(int number) {
                number1 = number;

            }
        });


    }


    //增
    private void insertData() {
        users = mUserDao.loadAll();


        Log.e("TAG", "insertData:   " + number1);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == javasBean.getId()) {

                javasBean.setShuliang(users.get(i).getShuliang() + number1);
                mUserDao.update(javasBean);
                return;
            }

        }
        javasBean.setShuliang(number1 );
        mUserDao.insert(javasBean);//添加一个


    }

    @OnClick({R.id.queding, R.id.gouwuche})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.queding:

                initJiaGe();
                insertData(); //保存到数据库

                break;
            case R.id.gouwuche:
                //跳转到购物车
                users = mUserDao.loadAll();
                for (int i = 0; i < users.size(); i++) {
                    Log.e("TAG", "跳转到购物车之前 个数 "+users.get(i).getShuliang() );
                }
                Intent intent = new Intent(TestActivity.this , GouWuCheActivity.class);

                startActivity(intent);
                break;
        }
    }
}
