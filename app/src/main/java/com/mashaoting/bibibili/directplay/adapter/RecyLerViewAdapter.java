package com.mashaoting.bibibili.directplay.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.directplay.bean.DirectplayZBBean;
import com.mashaoting.bibibili.directplay.dianjitiaozhuanyemian.BannerInfoActivity;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.AccordionTransformer;

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
    private final DirectplayZBBean.DataBean dataBean;
    private final LayoutInflater inflater;

    public RecyLerViewAdapter(Context context, DirectplayZBBean.DataBean data) {
        this.context = context;
        dataBean = data;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getItemViewType(int position) {

        if (position == BANNER) {  // 横幅
            currentType = BANNER;

        } else if (position == CHANNEL) {  //频道
            currentType = CHANNEL;
        }
//        else if (position == ACT) {  //活动
//            currentType = ACT;
//        } else if (position == SECKILL) {  // 秒杀
//            currentType = SECKILL;
//        } else if (position == RECOMMEND) {  //推荐
//            currentType = RECOMMEND;
//        } else if (position == HOT) {  //热卖
//            currentType = HOT;
//        }
        return currentType; //当前类型
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {  // 横幅

            return new BannerViewHolder(context, inflater.inflate(R.layout.banner_viewpager, null));

        }else if(viewType == BANNER) {
//            return new SmiloTetleViewHolder(context, inflater.inflate(R.layout.smilo_tetle, null));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {  // 横幅
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            //设置数据Banner的数据
            bannerViewHolder.setData(dataBean.getBanner());

        }
//        else if(getItemViewType(position) ==) {
//
//        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }


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
//                                    .fitCenter()
//                                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
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
                    context.startActivity(intent);
                }
            });
        }
    }
}
