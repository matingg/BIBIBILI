package com.mashaoting.bibibili.pursueplay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.pursueplay.bean.ZhuiFanBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by 麻少亭 on 2017/3/23.
 */

public class ZHRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private final Context context;
    private final ZhuiFanBean.ResultBean datas;
    private final LayoutInflater layoutInflater;
    private static final int ASD = 0;
    private static final int ASF = 1;

    private int currenttype;


    public ZHRecyclerViewAdapter(Context context, ZhuiFanBean.ResultBean result) {
        this.context = context;
        this.datas = result;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == ASD) {
            currenttype = ASD;
            return currenttype;
        }
//        else if (position == ASF) {
//            currenttype = ASF;
//            return currenttype;
//
//        }
        return currenttype;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (ASD == viewType) {

            return new ViewHolder(context, layoutInflater.inflate(R.layout.recyclerview_item, null));

        } else if (ASF == viewType) {

//            return new ViewHolder(context, layoutInflater.inflate(R.layout.recyclerview_2_item, null));

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ASD) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.setData(context, datas.getPrevious().getList());
        } else if (getItemViewType(position) == ASF) {
//            ViewHolder2 viewholder2 = (ViewHolder2) holder;
//            viewholder2.setData(context, datas.getAd().getHead());
        }


    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        @InjectView(R.id.recylerview_2_item_iv)
        GridView recylerview2ItemIv;

        ViewHolder2(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void setData(Context context, List<ZhuiFanBean.ResultBean.AdBean.HeadBean> head) {
            GridViewAdapter2 adapter = new GridViewAdapter2(context, head);
            recylerview2ItemIv.setAdapter(adapter);
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        @InjectView(R.id.gv_zhuifan)
        GridView gvZhuifan;
        @InjectView(R.id.rl_fanju)
        RelativeLayout rlFanju;
        @InjectView(R.id.rl_guoju)
        RelativeLayout rlGuoju;
        @InjectView(R.id.tv_time)
        TextView tvTime;
        @InjectView(R.id.tv_suoyin)
        TextView tvSuoyin;
        @InjectView(R.id.look_tuijian)
        ImageView lookTuijian;

        ViewHolder(Context context, View view) {
            super(view);
            ButterKnife.inject(this, view);
            this.context = context;

        }


        public void setData(Context context, List<ZhuiFanBean.ResultBean.PreviousBean.ListBean> list) {
            ZFGridAdapter adapter = new ZFGridAdapter(context, list);
            gvZhuifan.setAdapter(adapter);
        }
    }

    @OnClick({R.id.rl_fanju, R.id.rl_guoju, R.id.tv_time, R.id.tv_suoyin, R.id.look_tuijian})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_fanju:
                Toast.makeText(context, "番剧", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_guoju:
                Toast.makeText(context, "国剧", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_time:
                Toast.makeText(context, "时间表", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_suoyin:
                Toast.makeText(context, "索引", Toast.LENGTH_SHORT).show();
                break;
            case R.id.look_tuijian:
                Toast.makeText(context, "看看推荐", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}


