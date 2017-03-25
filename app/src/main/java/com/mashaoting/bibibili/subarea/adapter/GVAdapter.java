package com.mashaoting.bibibili.subarea.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.directplay.dianjitiaozhuanyemian.CornersTransform;
import com.mashaoting.bibibili.subarea.bean.FenQuBean;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 麻少亭 on 2017/3/24.
 */

public class GVAdapter extends BaseAdapter {


    private final FenQuBean.DataBean dataBean;
    private Context context;



    public GVAdapter(Context context, FenQuBean.DataBean dataBean) {
        this.context = context;

        this.dataBean = dataBean;
    }

    @Override
    public int getCount() {
        return dataBean.getBody().size();
    }

    @Override
    public Object getItem(int position) {
        return dataBean.getBody().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.grald_ooo, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textview0.setText(dataBean.getTitle());
        viewHolder.textview0.setText(dataBean.getBody().get(position).getTitle());
        Glide.with(context).load(dataBean.getBody().get(position).getCover()).transform(new CornersTransform(context)).into(viewHolder.ivGridview0);
        viewHolder.textRenshu2.setText("" + dataBean.getBody().get(position).getPlay() + "");
        return convertView;
    }

    class ViewHolder {
        @InjectView(R.id.iv_gridview_0)
        ImageView ivGridview0;
        @InjectView(R.id.textview_0)
        TextView textview0;
        @InjectView(R.id.text_renshu_0)
        TextView textRenshu0;
        @InjectView(R.id.text_renshu_2)
        TextView textRenshu2;
        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
