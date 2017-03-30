package com.mashaoting.bibibili.subarea.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.anye.greendao.gen.JavasBeanDao;
import com.bumptech.glide.Glide;
import com.mashaoting.bibibili.MyApplication;
import com.mashaoting.bibibili.R;
import com.mashaoting.bibibili.subarea.bean.JavasBean;
import com.mashaoting.bibibili.subarea.gouwuche.CartStorage;
import com.mashaoting.bibibili.subarea.jiajianhao.IncreaseReduceTextView;

import java.util.Iterator;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.R.attr.value;

/**
 * Created by shkstart on 2017/3/29.
 */

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {


    private TextView tvShopcartTotal;
    private final Context context;
    private final List<JavasBean> datas;
    private final CheckBox checkboxAll;
    private final CheckBox checkboxDeleteAll;
    private JavasBean javasBean;
    private JavasBeanDao mUserDao;

    public RecyclerviewAdapter(Context context, List<JavasBean> users, TextView tvShopcartTotal, CheckBox checkboxAll, CheckBox checkboxDeleteAll) {
        this.context = context;
        datas = users;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        this.checkboxDeleteAll = checkboxDeleteAll;
        mUserDao = MyApplication.getInstances().getDaoSession().getJavasBeanDao();
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.item_shop_cart, null));
    }

    private List<JavasBean> users;

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //先得到数据  javasBean
        javasBean = datas.get(position);
//2.绑定数据
        holder.cbGov.setChecked(javasBean.getIsadd());
        //图片
        Glide.with(context).load(javasBean.getTuid()).into(holder.ivGov);
        //设置名称
        holder.tvDescGov.setText(javasBean.getTou());
        //设置价格
        holder.tvPriceGov.setText("￥" + javasBean.getDanmuku());

        //设置数量
        holder.jisuan.setNumber(javasBean.getShuliang());

//        holder.jisuan.set(1);
        //设置库存-来自服务器-
//        holder.addSubView.setMaxValue(100);

        holder.jisuan.setOnNumberChangeListener(new IncreaseReduceTextView.OnNumberChangeListener() {
            @Override
            public void onNumberChange(int number) {
                //1.回调数量
                javasBean.setShuliang(number);
//                mUserDao.update(javasBean);
//                users = mUserDao.loadAll();
                //2.持久化
                CartStorage.getInstance(context).updateData(javasBean);
                //3.显示总价格
                showTotalPrice();
            }
        });
        //回传过来的值


    }

    public void showTotalPrice() {
        //显示总价格
        tvShopcartTotal.setText("合计:" + getTotalPrice());
    }

    /**
     * 返回总价格
     *
     * @return
     */
    public double getTotalPrice() {
        double totalPrice = 0;
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                //遍历出来
                JavasBean javasBean = datas.get(i);
                //必须是选择状态
                if (javasBean.getIsadd()) {
                    totalPrice += Double.parseDouble(javasBean.getDanmuku() + "") * javasBean.getShuliang();
                }
            }
        }
        return totalPrice;
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }


    /**
     * 校验是否全选
     */
    public void checkAll() {
        if (datas != null && datas.size() > 0) {

            int number = 0;
            for (int i = 0; i < datas.size(); i++) {
                JavasBean javasBean = datas.get(i);
                if (!javasBean.getIsadd()) {
                    //只要有一个不勾选
                    checkboxAll.setChecked(false);
                    checkboxDeleteAll.setChecked(false);
                } else {
                    //勾选
                    number++;
                }
            }

            //勾选的数量和购物车的条目相同就全选
            if (datas.size() == number) {
                checkboxAll.setChecked(true);
                checkboxDeleteAll.setChecked(true);
            }

        } else {
            //没有数据
            checkboxAll.setChecked(false);
            checkboxDeleteAll.setChecked(false);
        }
    }

    /**
     * 删除数据
     */
    public void deleteData() {
//        if(datas != null && datas.size() >0){
//            for (int i=0;i<datas.size();i++){
//                GoodsBean goodsBean = datas.get(i);
//                //当勾选的才删除
//                if(goodsBean.isChecked()){
//                    //1.内存中删除
//                    datas.remove(goodsBean);
//                    //2.本地也好保持
//                    CartStorage.getInstance(mContext).deleteData(goodsBean);
//                    //刷新数据
//                    notifyItemRemoved(i);
//                    i--;
//                }
//            }
//        }


        if (datas != null && datas.size() > 0) {
            for (Iterator iterator = datas.iterator(); iterator.hasNext(); ) {
                JavasBean javaBean = (JavasBean) iterator.next();
                //是否选中，选中才删除哦
                if (javaBean.getIsadd()) {

                    int position = datas.indexOf(javaBean);
                    //从内存中移除
                    iterator.remove();
                    mUserDao.deleteByKey(javaBean.getId());
                    //本地也要同步
                    CartStorage.getInstance(context).deleteData(javaBean);

                    //刷新页面
                    notifyItemRemoved(position);

                }
            }
        }

    }


    public void checkAll_none(boolean isChecked) {
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                //把购物车里面的Bean对象都设置勾选或者非勾选
                JavasBean javasBean = datas.get(i);
                //设置是否勾选状态
                javasBean.setIsadd(isChecked);
                //设置CheckBox的状态
                checkboxAll.setChecked(isChecked);
                checkboxDeleteAll.setChecked(isChecked);

                //更新视图
                notifyItemChanged(i);
            }

        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.cb_gov)
        CheckBox cbGov;
        @InjectView(R.id.iv_gov)
        ImageView ivGov;
        @InjectView(R.id.tv_desc_gov)
        TextView tvDescGov;
        @InjectView(R.id.tv_price_gov)
        TextView tvPriceGov;
        @InjectView(R.id.jisuan)
        IncreaseReduceTextView jisuan;

        //        @InjectView(R.id.addSubView)
//        IncreaseReduceTextView addSubView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            //在这个地方设置item的点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //回调接口
                    if (itemClickListener != null) {
                        itemClickListener.onItemClickListener(v, getLayoutPosition());
                    }
                }
            });
        }
    }

    //回调点击事件的监听
    private OnItemClickListener itemClickListener;

    /**
     * 点击item的监听
     */
    public interface OnItemClickListener {
        public void onItemClickListener(View view, int position);
    }

    /**
     * 设置item的监听
     *
     * @param l
     */
    public void setOnItemClickListener(OnItemClickListener l) {
        this.itemClickListener = l;
    }


}
