package com.mashaoting.bibibili.subarea.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.subarea.bean.FenQuBean;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.AccordionTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 麻少亭 on 2017/3/24.
 */

public class FenQuRecyclerAdapter extends RecyclerView.Adapter {


    private final Context context;
//    private final List<FenQuBean.DataBean> dataBeen;

    private static final int ASD0 = 0;
    private static final int ASD1 = 1;
    private static final int ASD2 = 2;


    private int CURRENTTYPE;
    private static List<String> imageUrls;
    private List<FenQuBean.DataBean> dataBeen;


    public FenQuRecyclerAdapter(Context context, List<FenQuBean.DataBean> data) {
        this.context = context;
        dataBeen = data;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == ASD0) {
            CURRENTTYPE = ASD0;
        } else if (position == ASD1) {
            CURRENTTYPE = ASD1;
        } else if (position == ASD2) {
            CURRENTTYPE = ASD2;
        }

        return CURRENTTYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (dataBeen.size() != 0) {


            if (viewType == ASD0) {
                View view = View.inflate(context, R.layout.gridview, null);
                return new ViewHolder0(context, view);

            } else if (viewType == ASD1) {
                View view = View.inflate(context, R.layout.gridview, null);
                return new ViewHolder0(context, view);

            } else if (viewType == ASD2) {
                View view = View.inflate(context, R.layout.gridview, null);
                return new ViewHolder0(context, view);

            }

        }
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if (position == ASD0) {

            ViewHolder0 viewholder0 = (ViewHolder0) holder;
            viewholder0.setData(dataBeen.get(0));
        } else if (position == ASD1) {
            ViewHolder0 viewholder0 = (ViewHolder0) holder;
            viewholder0.setData(dataBeen.get(1));
        } else if (position == ASD2) {
            ViewHolder0 viewholder0 = (ViewHolder0) holder;
            viewholder0.setData(dataBeen.get(2));
        }


    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public void refreshAdapter(List<FenQuBean.DataBean> data) {
        imageUrls.clear();
        dataBeen.clear();

        dataBeen.addAll(data);
        notifyDataSetChanged();
    }

    static class ViewHolder0 extends RecyclerView.ViewHolder {
        @InjectView(R.id.gridviewrecyl)
        GridView gridviewrecyl;
        @InjectView(R.id.banner)
        Banner banner;
        @InjectView(R.id.shenmequ)
        TextView shenmequ;
        @InjectView(R.id.jinqukankan)
        TextView jinqukankan;
        @InjectView(R.id.gengduo)
        TextView gengduo;
        @InjectView(R.id.dongtai)
        TextView dongtai;
        private Context context;

        ViewHolder0(Context context, View view) {
            super(view);
            ButterKnife.inject(this, view);
            this.context = context;

        }

        public void setData(final FenQuBean.DataBean dataBean) {
            GVAdapter adapter = new GVAdapter(context, dataBean);
            gridviewrecyl.setAdapter(adapter);
            shenmequ.setText(dataBean.getTitle());

            gengduo.setText("更多" + dataBean.getTitle());


            if (dataBean.getBanner() != null) {

                //准备图片集合
                imageUrls = new ArrayList<>();
                for (int i = 0; i < dataBean.getBanner().getBottom().size(); i++) {

                    imageUrls.add(dataBean.getBanner().getBottom().get(i).getImage());
                }
                //简单使用
                banner.setImages(imageUrls)
                        .setImageLoader(new ImageLoader() {
                            @Override
                            public void displayImage(Context context, Object path, ImageView imageView) {

                                Glide.with(context)
                                        .load(path)
                                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                        .crossFade()
                                        .into(imageView);
                            }
                        }).start();

                //设置动画效果-手风琴效果
                banner.setBannerAnimation(AccordionTransformer.class);

                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        if (imageUrls != null) {
                            Toast.makeText(context, "被点击了" + "" + dataBean.getBanner().getBottom().get(position).getTitle(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            } else {
                banner.setVisibility(View.GONE);
            }


        }


    }

}
