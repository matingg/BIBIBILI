package com.mashaoting.bibibili.subarea.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.subarea.bean.FenQuBean;
import com.youth.banner.Banner;
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
    private final List<FenQuBean.DataBean> dataBeen;

    private static final int ASD0 = 0;
    private static final int ASD1 = 1;
    private static final int ASD2 = 2;
    private static final int ASD3 = 3;
    private static final int ASD4 = 4;
    private static final int ASD5 = 5;
    private static final int ASD6 = 6;
    private static final int ASD7 = 7;
    private static final int ASD8 = 8;
    private static final int ASD9 = 9;
    private static final int ASD10 = 10;
    private static final int ASD11 = 11;
    private static final int ASD12 = 12;
    private static final int ASD13 = 13;
    private static final int ASD14 = 14;
    private static final int ASD15 = 15;


    private int CURRENTTYPE;
    private static List<String> imageUrls;

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
        } else if (position == ASD3) {
            CURRENTTYPE = ASD3;
        } else if (position == ASD4) {
            CURRENTTYPE = ASD4;
        } else if (position == ASD5) {
            CURRENTTYPE = ASD5;
        } else if (position == ASD6) {
            CURRENTTYPE = ASD6;
        } else if (position == ASD7) {
            CURRENTTYPE = ASD7;
        } else if (position == ASD8) {
            CURRENTTYPE = ASD8;
        } else if (position == ASD9) {
            CURRENTTYPE = ASD9;
        } else if (position == ASD10) {
            CURRENTTYPE = ASD10;
        } else if (position == ASD11) {
            CURRENTTYPE = ASD11;
        } else if (position == ASD12) {
            CURRENTTYPE = ASD12;
        } else if (position == ASD13) {
            CURRENTTYPE = ASD13;
        } else if (position == ASD14) {
            CURRENTTYPE = ASD14;
        } else if (position == ASD15) {
            CURRENTTYPE = ASD15;
        }

        return CURRENTTYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ASD0) {
            View view = View.inflate(context, R.layout.gridview, null);
            return new ViewHolder0(context, view);

        } else if (viewType == ASD1) {
            View view = View.inflate(context, R.layout.gridview, null);
            return new ViewHolder0(context, view);

        } else if (viewType == ASD2) {
            View view = View.inflate(context, R.layout.gridview, null);
            return new ViewHolder0(context, view);

        } else if (viewType == ASD3) {
            View view = View.inflate(context, R.layout.gridview, null);
            return new ViewHolder0(context, view);

        }else if (viewType == ASD4) {
            View view = View.inflate(context, R.layout.gridview, null);
            return new ViewHolder0(context, view);

        } else if (viewType == ASD5) {
            View view = View.inflate(context, R.layout.gridview, null);
            return new ViewHolder0(context, view);

        } else if (viewType == ASD6) {
            View view = View.inflate(context, R.layout.gridview, null);
            return new ViewHolder0(context, view);

        }else if (viewType == ASD7) {
            View view = View.inflate(context, R.layout.gridview, null);
            return new ViewHolder0(context, view);

        } else if (viewType == ASD8) {
            View view = View.inflate(context, R.layout.gridview, null);
            return new ViewHolder0(context, view);

        } else if (viewType == ASD9) {
            View view = View.inflate(context, R.layout.gridview, null);
            return new ViewHolder0(context, view);

        }else if (viewType == ASD10) {
            View view = View.inflate(context, R.layout.gridview, null);
            return new ViewHolder0(context, view);

        } else if (viewType == ASD11) {
            View view = View.inflate(context, R.layout.gridview, null);
            return new ViewHolder0(context, view);

        } else if (viewType == ASD12) {
            View view = View.inflate(context, R.layout.gridview, null);
            return new ViewHolder0(context, view);

        }else if (viewType == ASD13) {
            View view = View.inflate(context, R.layout.gridview, null);
            return new ViewHolder0(context, view);

        } else if (viewType == ASD14) {
            View view = View.inflate(context, R.layout.gridview, null);
            return new ViewHolder0(context, view);

        } else if (viewType == ASD15) {
            View view = View.inflate(context, R.layout.gridview, null);
            return new ViewHolder0(context, view);

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
        }else if(position == ASD3) {
            ViewHolder0 viewholder0 = (ViewHolder0) holder;
            viewholder0.setData(dataBeen.get(3));
        }


    }

    @Override
    public int getItemCount() {
        return 15;
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

        public void setData(FenQuBean.DataBean dataBean) {
            if(dataBean.getParam() != null) {
                GVAdapter adapter = new GVAdapter(context, dataBean);
                gridviewrecyl.setAdapter(adapter);
                shenmequ.setText(dataBean.getTitle());
            }else {
                gridviewrecyl.setVisibility(View.GONE);
            }
            if (dataBean.getBanner() != null) {
                int a = 1 ;
                if(dataBean.getBanner().getBottom().size() < 2) {
                    a= 1 ;
                }else {
                    a = dataBean.getBanner().getBottom().size();
                }
                //准备图片集合
                imageUrls = new ArrayList<>();
                for (int i = 0; i < a; i++) {
                    imageUrls.add(dataBean.getBanner().getBottom().get(i).getImage());
                }

            } else {
                banner.setVisibility(View.GONE);
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


        }
    }
}
