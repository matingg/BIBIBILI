package com.mashaoting.bibibili.directplay.dianjitiaozhuanyemian;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.directplay.bean.DirectplayZBBean;
import com.mashaoting.bibibili.shipin.DanmakuVideoPlayer;
import com.mashaoting.bibibili.shipin.DanmkuVideoActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 麻少亭 on 2017/3/22.
 */

public class GridViewAdapter extends BaseAdapter {


    private final List<DirectplayZBBean.DataBean.PartitionsBean.LivesBean> dataBean;
    public Context context;
    private DirectplayZBBean.DataBean.PartitionsBean.LivesBean livesBean;

    public GridViewAdapter(Context context, List<DirectplayZBBean.DataBean.PartitionsBean.LivesBean> lives) {
        this.context = context;
        dataBean = lives;
    }


    @Override
    public int getCount() {
        return dataBean.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.gridview_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }
        viewHolder = (ViewHolder) convertView.getTag();
        livesBean = dataBean.get(position);

        viewHolder.tvGridviewBiaoti.setText(livesBean.getTitle());
        viewHolder.tvName.setText(livesBean.getOwner().getName());
        viewHolder.guanzhonggeshu.setText(""+livesBean.getOnline() );
        Glide.with(context).load(livesBean.getCover().getSrc())
                .transform(new CornersTransform(context))
                .error(R.drawable.ic_header_activity_center)
                .crossFade()
                .into(viewHolder.ivGridview);
        return convertView;
    }

     class ViewHolder {
        @InjectView(R.id.iv_gridview)
        ImageView ivGridview;
        @InjectView(R.id.tv_gridview_biaoti)
        TextView tvGridviewBiaoti;
        @InjectView(R.id.tv_name)
        TextView tvName8;
        @InjectView(R.id.tvname_)
        TextView tvName;
        @InjectView(R.id.guanzhonggeshu)
        TextView guanzhonggeshu;
        @InjectView(R.id.cardView)
        CardView cardView;
        @InjectView(R.id.linear_dianjibofang)
        LinearLayout linearDianjibofang;


        ViewHolder(View view) {
            ButterKnife.inject(this, view);
            cardView.setRadius(20);//设置图片圆角的半径大小

            cardView.setCardElevation(20);//设置阴影部分大小

            cardView.setContentPadding(15, 15, 15, 15);//设置图片距离阴影大小
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context , DanmkuVideoActivity.class);
//
//                    context.startActivity(intent);
//                }
//            });


        }


    }
}
