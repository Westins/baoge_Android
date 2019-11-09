package com.example.family.myapplicationtaobao;

/**
 * Created by Family on 2019/6/17.
 */
//关注实体类
public class GuanZhupojo {
    private int id;  //店铺id
    private int gz_tp; //店铺头像
    private String gz_gl; //单选框
    private String gz_name; //店铺名字

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGz_tp() {
        return gz_tp;
    }

    public void setGz_tp(int gz_tp) {
        this.gz_tp = gz_tp;
    }

    public String getGz_gl() {
        return gz_gl;
    }

    public void setGz_gl(String gz_gl) {
        this.gz_gl = gz_gl;
    }

    public String getGz_name() {
        return gz_name;
    }

    public void setGz_name(String gz_name) {
        this.gz_name = gz_name;
    }
}
