package com.mashaoting.bibibili.subarea.gouwuche;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashaoting.bibibili.subarea.bean.JavasBean;
import com.mashaoting.bibibili.utils.CacheUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shkstart on 2017/3/30.
 */

public class CartStorage {

    public static final String JSON_CART = "json_cart";
    private static CartStorage instace;
    private final Context mContext;
    //SparseArray替代HashMap-在内存中
    private SparseArray<JavasBean> sparseArray;

    private CartStorage(Context context) {
        this.mContext = context;
        sparseArray = new SparseArray();
        //从本地获取数据
        listToSparseArray();
    }

    /**
     * 把List的数据转换成SparseArray
     */
    private void listToSparseArray() {

        //从本地读取的集合数据
        List<JavasBean> beanList = getAllData();
        //循环起来，把数据转存到sparseArray
        for (int i = 0; i < beanList.size(); i++) {
            JavasBean JavasBean = beanList.get(i);
            sparseArray.put(Integer.parseInt(JavasBean.getId()+""), JavasBean);
        }

    }

    /**
     * 得到所有的数据
     *
     * @return
     */
    public List<JavasBean> getAllData() {

        return getLocalData();
    }

    /**
     * 得到本地缓存的数据
     *
     * @return
     */
    private List<JavasBean> getLocalData() {
        List<JavasBean> goodsBeens = new ArrayList<>();

        //从本地获取数据  -从sp
        String json = CacheUtils.getString(mContext, JSON_CART);//保持的json数据

        if (!TextUtils.isEmpty(json)) {
            //把它转化成列表
            goodsBeens = new Gson().fromJson(json, new TypeToken<List<JavasBean>>() {

            }.getType());
        }
        //把json数据解析成List数据

        return goodsBeens;
    }


    /**
     * 懒汉模式
     *
     * @param context
     * @return
     */
    public static CartStorage getInstance(Context context) {
        if (instace == null) {
            synchronized (CartStorage.class) {
                if (instace == null) {
                    instace = new CartStorage(context);
                }
            }
        }
        return instace;

    }

    /**
     * 添加数据
     * @param JavasBean
     */
    public void addData(JavasBean JavasBean) {
        //1.数据添加到sparseArray
        JavasBean tempJavasBean = sparseArray.get(Integer.parseInt(JavasBean.getId()+""));
        //已经保存过
        if (tempJavasBean != null) {
            tempJavasBean.setShuliang( JavasBean.getShuliang());
        } else {
            //没有添加过
            tempJavasBean = JavasBean;
        }

        //添加到集合中-内存
        sparseArray.put(Integer.parseInt(tempJavasBean.getId()+""), tempJavasBean);


        //2.保持到本地-持久化
        saveLocal();
    }

    /**
     * 删除数据
     * @param JavasBean
     */
    public  void deleteData(JavasBean JavasBean){
        //1.删除数据
        sparseArray.delete(Integer.parseInt(JavasBean.getId()+""));

        //2.保持到本地
        saveLocal();
    }

    /**
     * 修改数据
     * @param JavasBean
     */
    public  void updateData(JavasBean JavasBean){
        //1.删除数据
        sparseArray.put(Integer.parseInt(JavasBean.getId()+""),JavasBean);

        //2.保持到本地
        saveLocal();
    }

    /**
     * 保持到本地
     */
    private void saveLocal() {
        //1.把sparseArray转成List
        List<JavasBean> JavasBeanList = sparseArrayToList();
        //2.使用Gson把List转json的String类型数据
        String  savaJson = new Gson().toJson(JavasBeanList);
        //3.使用CacheUtils缓存数据
        CacheUtils.setString(mContext,JSON_CART,savaJson);

    }

    /**
     * 把sparseArray转成List
     * @return
     */
    private List<JavasBean> sparseArrayToList() {
        //列表数据
        List<JavasBean> JavasBeanList = new ArrayList<>();

        for (int i = 0; i < sparseArray.size(); i++) {
            JavasBean JavasBean = sparseArray.valueAt(i);
            JavasBeanList.add(JavasBean);
        }

        return JavasBeanList;
    }

    /**
     * 是否在购物车中存在
     * @param product_id
     * @return
     */
    public JavasBean findDete(String product_id) {
        return  sparseArray.get(Integer.parseInt(product_id));

    }
}
