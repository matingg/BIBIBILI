package com.mashaoting.bibibili.subarea.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.subarea.bean.FenQuBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 麻少亭 on 2017/3/24.
 */

public class FenQuRecyclerAdapter extends RecyclerView.Adapter {


    private final Context context;


    private static final int ASD = 0;
    private static final int ASF = 1;
    private final List<FenQuBean.DataBean.NewBean> newBeen;
    private int currenttype;
    private final LayoutInflater layoutInflater;

    public FenQuRecyclerAdapter(Context context, List<FenQuBean.DataBean.NewBean> newX) {
        this.context = context;
        newBeen = newX;
        layoutInflater = LayoutInflater.from(context);

    }


    @Override
    public int getItemViewType(int position) {
        if (position == ASD) {
            currenttype = ASD;
            return currenttype;
        } else if (position == ASF) {
            currenttype = ASF;
            return currenttype;

        }
        return currenttype;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


//        View convertView = layoutInflater.inflate(R.layout.item, null, false); // if use parent, only first line will show up
        if (ASD == viewType) {
            return new ViewHolder1(context, layoutInflater.inflate(R.layout.recyclerview_fenqu_item, null));


        } else if (ASF == viewType) {

//            return new ViewHolder(context, layoutInflater.inflate(R.layout.recyclerview_2_item, null));

        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ASD) {
            ViewHolder1 viewHolder1 = (ViewHolder1) holder;
            viewHolder1.setData(context, newBeen);

        } else if (getItemViewType(position) == ASF) {


        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }


    static class ViewHolder1 extends RecyclerView.ViewHolder {
        private final Context context;
        @InjectView(R.id.gv_fenqupf)
        GridView gvFenqupf;

        ViewHolder1(Context context, View view) {
            super(view);
            this.context = context;
            ButterKnife.inject(this, view);
        }


        public void setData(Context context, List<FenQuBean.DataBean.NewBean> newBeen) {
            GVAdapter adapter = new GVAdapter(context, newBeen);
            gvFenqupf.setAdapter(adapter);
        }
    }
}
