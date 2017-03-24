package com.mashaoting.bibibili.pursueplay.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.pursueplay.bean.ZhuiFanBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 麻少亭 on 2017/3/24.
 */

public class GridViewAdapter2 extends BaseAdapter {

    private final Context context;
    private final List<ZhuiFanBean.ResultBean.AdBean.HeadBean> datas;

    public GridViewAdapter2(Context context, List<ZhuiFanBean.ResultBean.AdBean.HeadBean> head) {
        this.context = context;
        datas = head;
    }


    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.zf_grid2_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ZhuiFanBean.ResultBean.AdBean.HeadBean headBean = datas.get(position);
        viewHolder.tvName2Zf.setText(headBean.getTitle());
        Glide.with(context).load(headBean.getImg()).into(viewHolder.ivZfItem2Tupian);


        return convertView;
    }


     class ViewHolder {
        @InjectView(R.id.iv_zf_item2_tupian)
        ImageView ivZfItem2Tupian;
        @InjectView(R.id.tv_ren_2_shu)
        TextView tvRen2Shu;
        @InjectView(R.id.tv_name2_zf)
        TextView tvName2Zf;
        @InjectView(R.id.tv_gengxin2)
        TextView tvGengxin2;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}