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
 * Created by 麻少亭 on 2017/3/23.
 */

public class ZFGridAdapter extends BaseAdapter {


    private final Context context;
    private final List<ZhuiFanBean.ResultBean.PreviousBean.ListBean> datas;



    public ZFGridAdapter(Context context, List<ZhuiFanBean.ResultBean.PreviousBean.ListBean> list) {
        this.context = context;
        datas = list;
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
            convertView = View.inflate(context, R.layout.zf_grid_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ZhuiFanBean.ResultBean.PreviousBean.ListBean listBean = datas.get(position);
        Glide.with(context).load(listBean.getCover()).into(viewHolder.ivZfItemTupian);
        viewHolder.tvNameZf.setText(listBean.getTitle());
        viewHolder.tvGengxin.setText("更新至"+listBean.getNewest_ep_index()+"话");
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_zf_item_tupian)
        ImageView ivZfItemTupian;
        @InjectView(R.id.tv_ren_o_shu)
        TextView tvRenOShu;
        @InjectView(R.id.tv_name_zf)
        TextView tvNameZf;
        @InjectView(R.id.tv_gengxin)
        TextView tvGengxin;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
