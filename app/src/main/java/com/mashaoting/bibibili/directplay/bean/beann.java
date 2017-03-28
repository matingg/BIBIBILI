package com.mashaoting.bibibili.directplay.bean;

import java.io.Serializable;

/**
 * Created by shkstart on 2017/3/29.
 */

public class Beann implements Serializable {
    private String title;
    private String face;
    private String playurl;

    public Beann(String title, String face, String playurl) {
        this.title = title;
        this.face = face;
        this.playurl = playurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getPlayurl() {
        return playurl;
    }

    public void setPlayurl(String playurl) {
        this.playurl = playurl;
    }

    public Beann() {
    }

}
