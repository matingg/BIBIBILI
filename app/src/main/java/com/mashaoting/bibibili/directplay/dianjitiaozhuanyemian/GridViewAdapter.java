package com.mashaoting.bibibili.directplay.dianjitiaozhuanyemian;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.directplay.bean.DirectplayZBBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 麻少亭 on 2017/3/22.
 */

public class GridViewAdapter extends BaseAdapter {

    private final List<DirectplayZBBean.DataBean.PartitionsBean> dataBean;
    private Context context;
    private DirectplayZBBean.DataBean.PartitionsBean partitionsBean;

    public GridViewAdapter(Context context, List<DirectplayZBBean.DataBean.PartitionsBean> partitions) {
        this.context = context;
        dataBean = partitions;
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
        ViewHolder viewHolder ;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.gridview_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        partitionsBean = dataBean.get(position);

        viewHolder.tvGridviewBiaoti.setText(partitionsBean.getLives().get(position).getTitle());
        viewHolder.guanzhonggeshu.setText("房间号:"+partitionsBean.getLives().get(position).getRoom_id()+"");
        Glide.with(context).load(partitionsBean.getLives().get(position).getCover().getSrc()).into(viewHolder.ivGridview);
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_gridview)
        ImageView ivGridview;
        @InjectView(R.id.tv_gridview_biaoti)
        TextView tvGridviewBiaoti;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.guanzhonggeshu)
        TextView guanzhonggeshu;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
