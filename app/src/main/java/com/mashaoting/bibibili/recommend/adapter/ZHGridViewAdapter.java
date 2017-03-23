package com.mashaoting.bibibili.recommend.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.recommend.bean.ZongHeBean;
import com.mashaoting.bibibili.utils.Utilstime;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 麻少亭 on 2017/3/23.
 */

public class ZHGridViewAdapter extends BaseAdapter {


    private final Context context;
    private List<ZongHeBean.DataBean> dataBeanList;

    public ZHGridViewAdapter(Context context, List<ZongHeBean.DataBean> data) {
        this.context = context;
        if(dataBeanList != null) {
            dataBeanList.clear();
        }

        dataBeanList = data;

    }

    @Override
    public int getCount() {
        return dataBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder ;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.zh_grid_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ZongHeBean.DataBean dataBean = dataBeanList.get(position);
           Utilstime utilstime = new Utilstime() ;
        viewHolder.tvTime.setText(utilstime.getDateTimeFromMillisecond((long) dataBean.getCtime()/3600));
        viewHolder.tvBiaoti.setText(dataBean.getTitle()); //标题
        viewHolder.tvLeixing.setText(dataBean.getTname());
        Log.e("TAG", "ZHGridViewAdapter getView()"+dataBean.getTname());
        Glide.with(context).load(dataBean.getCover()).into(viewHolder.ivZongheItemTupian);
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_zonghe_item_tupian)
        ImageView ivZongheItemTupian;
        @InjectView(R.id.tv_renshu)
        TextView tvRenshu;
        @InjectView(R.id.tv_pinglun)
        TextView tvPinglun;
        @InjectView(R.id.tv_time)
        TextView tvTime;
        @InjectView(R.id.tv_biaoti)
        TextView tvBiaoti;
        @InjectView(R.id.tv_leixing)
        TextView tvLeixing;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
