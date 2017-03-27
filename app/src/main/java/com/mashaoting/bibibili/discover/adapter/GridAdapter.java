package com.mashaoting.bibibili.discover.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.discover.bean.TagFaxianZonghe;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 麻少亭 on 2017/3/27.
 */

public class GridAdapter extends BaseAdapter {


    private final Context context;
    private final TagFaxianZonghe tagFaxianZonghe;
    private TagFaxianZonghe.DataBean.ItemsBean.ArchiveBean archiveBean;

    public GridAdapter(Context context, TagFaxianZonghe tagFaxianZonghe) {
        this.context = context;
        this.tagFaxianZonghe = tagFaxianZonghe;
    }

    @Override
    public int getCount() {
        return tagFaxianZonghe.getData().getItems().getArchive().size();
    }

    @Override
    public Object getItem(int position) {
        return tagFaxianZonghe.getData().getItems().getArchive().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.grid_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        archiveBean = tagFaxianZonghe.getData().getItems().getArchive().get(position);
        viewHolder.baioti.setText(archiveBean.getTitle());
        viewHolder.shui.setText(archiveBean.getAuthor());
        viewHolder.liulanliang.setText(archiveBean.getPlay() + "");
        viewHolder.pinglunliang.setText(archiveBean.getDanmaku() + "");
        Glide.with(context).load(archiveBean.getCover()).into(viewHolder.tupian);
        return convertView;
    }


    static class ViewHolder {
        @InjectView(R.id.tupian)
        ImageView tupian;
        @InjectView(R.id.baioti)
        TextView baioti;
        @InjectView(R.id.shui)
        TextView shui;
        @InjectView(R.id.liulanliang)
        TextView liulanliang;
        @InjectView(R.id.pinglunliang)
        TextView pinglunliang;
        @InjectView(R.id.cardView)
        CardView cardView;
        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }


}
