package com.mashaoting.bibibili.directplay.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.directplay.bean.Beann;
import com.mashaoting.bibibili.directplay.bean.DirectplayZBBean;
import com.mashaoting.bibibili.directplay.dianjitiaozhuanyemian.BannerInfoActivity;
import com.mashaoting.bibibili.directplay.dianjitiaozhuanyemian.GridViewAdapter;
import com.mashaoting.bibibili.directplay.fragment.MyGridView;
import com.mashaoting.bibibili.shipin.DanmkuVideoActivity;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.AccordionTransformer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 麻少亭 on 2017/3/22.
 */

public class RecyLerViewAdapter extends RecyclerView.Adapter {

    /**
     * 横幅广告
     */
    public static final int BANNER = 0;
    /**
     * 频道
     */
    public static final int CHANNEL = 1;

    /**
     * 活动
     */
    public static final int ACT = 2;

    /**
     * 秒杀
     */
    public static final int SECKILL = 3;
    /**
     * 推荐
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    public static final int HOT = 5;


    /**
     * 当前类型
     */
    public int currentType = BANNER;

    private final Context context;


    private final LayoutInflater inflater;
    private DirectplayZBBean.DataBean dataBean;

    public RecyLerViewAdapter(Context context, DirectplayZBBean.DataBean data) {
        this.context = context;
        dataBean = data;
        inflater = LayoutInflater.from(context);

    }

    public void refreshAdapter(DirectplayZBBean.DataBean data) {

        dataBean = data;
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {

        if (position == BANNER) {  // 横幅
            currentType = BANNER;

        } else if (position == CHANNEL) {  //频道
            currentType = CHANNEL;
        } else if (position == ACT) {  //活动
            currentType = ACT;
        }

        return currentType; //当前类型
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {  // 横幅
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            //设置数据Banner的数据
            bannerViewHolder.setData(dataBean.getBanner());

        } else if (getItemViewType(position) == CHANNEL) {
            SmiloTetleViewHolder bannerViewHolder = (SmiloTetleViewHolder) holder;

            bannerViewHolder.setData(dataBean.getPartitions().get(position).getPartition());

        } else if (getItemViewType(position) == ACT) {
            GridViewViewHolder bannerViewHolder = (GridViewViewHolder) holder;
            bannerViewHolder.setData(dataBean.getPartitions().get(0));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {  // 横幅

            return new BannerViewHolder(context, inflater.inflate(R.layout.banner_viewpager, null));

        } else if (viewType == CHANNEL) {
            return new SmiloTetleViewHolder(context, inflater.inflate(R.layout.smilo_tetle, null));
        } else if (viewType == ACT) {
            return new GridViewViewHolder(context, inflater.inflate(R.layout.grald_shenghuoyule, null));
        }

        return null;
    }


    @Override
    public int getItemCount() {
        return 3;
    }


    static class GridViewViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        @InjectView(R.id.gridview)
        MyGridView gridview;
        @InjectView(R.id.tv_zhuanqu)
        TextView tvZhuanqu;
        @InjectView(R.id.zhibogeshu)
        TextView zhibogeshu;
        @InjectView(R.id.iv_id_tupian)
        ImageView ivIdTupian;

        GridViewViewHolder(Context context, View view) {
            super(view);
            ButterKnife.inject(this, view);
            this.context = context;
        }

        public void setData(final DirectplayZBBean.DataBean.PartitionsBean partitionsBean) {
            tvZhuanqu.setText(partitionsBean.getPartition().getName());
            zhibogeshu.setText(partitionsBean.getPartition().getCount() + "");
            Glide.with(context).load(partitionsBean.getPartition().getSub_icon().getSrc()).into(ivIdTupian);
            GridViewAdapter adapter = new GridViewAdapter(context, partitionsBean.getLives());
            gridview.setAdapter(adapter);

            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Intent intent = new Intent(context , DanmkuVideoActivity.class);
//                    intent.putExtra("b" ,  partitionsBean.getLives().get(position).getPlayurl());
                    Intent intent = new Intent(context, DanmkuVideoActivity.class);
                    DirectplayZBBean.DataBean.PartitionsBean.LivesBean livesBean = partitionsBean.getLives().get(position);
                    String title = livesBean.getTitle();
                    String face = livesBean.getOwner().getFace();
                    String playurl = livesBean.getPlayurl();
                    Beann ai =  new Beann(title, face ,playurl);

                    intent.putExtra("src" , ai);
                    context.startActivity(intent);
                }
            });

        }
    }
}


class SmiloTetleViewHolder extends RecyclerView.ViewHolder {
    //    @InjectView(R.id.tv_zhuanqu)
//    TextView zhuangqu;
//    @InjectView(R.id.zhibogeshu)
//    TextView zhibogeshu;
    private Context context;

    SmiloTetleViewHolder(final Context context, View view) {
        super(view);
        ButterKnife.inject(this, view);
        this.context = context;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "view", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void setData(DirectplayZBBean.DataBean.PartitionsBean.PartitionBean name) {

    }


}


//----------------------------------------------------------------------------------------------------------------

//            banner   横幅
class BannerViewHolder extends RecyclerView.ViewHolder {
    private final Context context;
    @InjectView(R.id.banner)
    Banner banner;

    BannerViewHolder(Context context, View view) {
        super(view);
        ButterKnife.inject(this, view);
        this.context = context;
    }


    public void setData(final List<DirectplayZBBean.DataBean.BannerBean> bannerbean) {
        //准备图片集合
        List<String> imageUrls = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            imageUrls.add(bannerbean.get(0).getImg());
        }

        //简单使用
        banner.setImages(imageUrls)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {

                        Glide.with(context)
                                .load(path)
                                .crossFade()
                                .into(imageView);
                    }
                }).start();

        //设置动画效果-手风琴效果
        banner.setBannerAnimation(AccordionTransformer.class);


        //设置点击事件
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(context, "position" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, BannerInfoActivity.class);
                intent.putExtra("intent", bannerbean.get(0).getLink());
                intent.putExtra("tilte", bannerbean.get(0).getTitle());
                context.startActivity(intent);
            }
        });
    }
}



