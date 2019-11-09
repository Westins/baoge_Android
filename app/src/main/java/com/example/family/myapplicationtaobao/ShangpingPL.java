package com.example.family.myapplicationtaobao;

/**
 * Created by Family on 2019/6/14.
 */

//商品评论实体类
public class ShangpingPL {
    private int id;
    private int goodsId; //商品id
    private UserInfo userinfo; //评论人姓名
    private String pl_goumairq;//评论日期
    private String pl_goumaigg;//商品规格
    private String goodsReviewTest;//评论内容

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public UserInfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }

    public String getPl_goumairq() {
        return pl_goumairq;
    }

    public void setPl_goumairq(String pl_goumairq) {
        this.pl_goumairq = pl_goumairq;
    }

    public String getPl_goumaigg() {
        return pl_goumaigg;
    }

    public void setPl_goumaigg(String pl_goumaigg) {
        this.pl_goumaigg = pl_goumaigg;
    }

    public String getGoodsReviewTest() {
        return goodsReviewTest;
    }

    public void setGoodsReviewTest(String goodsReviewTest) {
        this.goodsReviewTest = goodsReviewTest;
    }
}
