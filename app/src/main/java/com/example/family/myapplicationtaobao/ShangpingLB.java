package com.example.family.myapplicationtaobao;

/**
 * Created by Family on 2019/6/6.
 */
//主页面 所有商品列表
public class ShangpingLB {
    private int goodsId; //商品id
    private String goodsImg; //商品预览图片
    private String goodsName;//商品名字
    private double goodsOriginalPrice;//商品价格
    private String goodsSalesVolume;//商品销量
    private String goodsModel;
    private int shopsId;

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public double getGoodsOriginalPrice() {
        return goodsOriginalPrice;
    }

    public void setGoodsOriginalPrice(double goodsOriginalPrice) {
        this.goodsOriginalPrice = goodsOriginalPrice;
    }

    public String getGoodsSalesVolume() {
        return goodsSalesVolume;
    }

    public void setGoodsSalesVolume(String goodsSalesVolume) {
        this.goodsSalesVolume = goodsSalesVolume;
    }

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
    }

    public int getShopsId() {
        return shopsId;
    }

    public void setShopsId(int shopsId) {
        this.shopsId = shopsId;
    }
}
