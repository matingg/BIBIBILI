package com.mashaoting.bibibili.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mashaoting.bibibili.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 麻少亭 on 2017/3/21.
 */

public class LisViewAdapter extends BaseAdapter {

    String[] strings = {"我的大会员", "会员积分", "离线缓存", "稍后再看", "我的收藏",
            "历史记录", "我的关注", "B币钱包", "主题选择", "设置与帮助"};
    ArrayList arrayList = new ArrayList();

    private final Context context;

    public LisViewAdapter(Context context) {
        this.context = context;
        for (int i = 0; i < strings.length; i++) {
            arrayList.add(strings[i]);
        }
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.zuoitem, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.leftMenuItemTv.setText(""+ arrayList.get(position));
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.left_menu_item_iv)
        ImageView leftMenuItemIv;
        @InjectView(R.id.left_menu_item_tv)
        TextView leftMenuItemTv;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
