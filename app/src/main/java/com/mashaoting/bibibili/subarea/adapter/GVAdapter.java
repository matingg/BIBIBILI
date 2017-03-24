package com.mashaoting.bibibili.subarea.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.subarea.bean.FenQuBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 麻少亭 on 2017/3/24.
 */

public class GVAdapter extends BaseAdapter {


    private final Context context;
    private final List<FenQuBean.DataBean.NewBean> newBeen;
    private FenQuBean.DataBean.NewBean newBean;


    public GVAdapter(Context context, List<FenQuBean.DataBean.NewBean> newBeen) {
        this.context = context;
        this.newBeen = newBeen;
    }


    @Override
    public int getCount() {
        return newBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return newBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.gv_item_1, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        newBean = newBeen.get(position);
        Log.e("TAG", "GVAdapte----------------r getView()"+newBean.getTitle());
        Glide.with(context).load(newBean.getCover()).into(viewHolder.ivZfItetupian);
        viewHolder.tvGengx.setText("评论："+newBean.getPlay());
        viewHolder.tvNamezf.setText(newBean.getTitle());
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_zf_itetupian)
        ImageView ivZfItetupian;
        @InjectView(R.id.tv_namezf)
        TextView tvNamezf;
        @InjectView(R.id.tv_gengx)
        TextView tvGengx;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
