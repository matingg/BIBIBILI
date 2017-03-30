package com.mashaoting.bibibili.subarea.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by shkstart on 2017/3/29.
 */

@Entity
public class JavasBean implements Serializable {
    @Id
    private long id ;
    private String tou ;
    private  String tuid  ;
    private int danmuku  ;
    private int shuliang = 1 ;
    private  boolean isadd = true ;

    @Transient
    private int tempUsageCount; // not persisted

    public boolean getIsadd() {
        return this.isadd;
    }

    public void setIsadd(boolean isadd) {
        this.isadd = isadd;
    }

    public int getShuliang() {
        return this.shuliang;
    }

    public void setShuliang(int shuliang) {
        this.shuliang = shuliang;
    }

    public int getDanmuku() {
        return this.danmuku;
    }

    public void setDanmuku(int danmuku) {
        this.danmuku = danmuku;
    }

    public String getTuid() {
        return this.tuid;
    }

    public void setTuid(String tuid) {
        this.tuid = tuid;
    }

    public String getTou() {
        return this.tou;
    }

    public void setTou(String tou) {
        this.tou = tou;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Generated(hash = 105262375)
    public JavasBean(long id, String tou, String tuid, int danmuku, int shuliang,
            boolean isadd) {
        this.id = id;
        this.tou = tou;
        this.tuid = tuid;
        this.danmuku = danmuku;
        this.shuliang = shuliang;
        this.isadd = isadd;
    }

    @Generated(hash = 1940190326)
    public JavasBean() {
    }


}
