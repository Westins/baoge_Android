package com.example.family.myapplicationtaobao;

/**
 * Created by Family on 2019/6/6.
 */
//用户订单
public class ShangpingDD {
    private int goodsId;//商品id
    private ShangpingLB goods;
    private int ordersInfoNum; //订单购买数量
    private String ordersInfoTime; //购买时间
    private String ordersInfoModel; //商品规格

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public ShangpingLB getGoods() {
        return goods;
    }

    public void setGoods(ShangpingLB goods) {
        this.goods = goods;
    }

    public int getOrdersInfoNum() {
        return ordersInfoNum;
    }

    public void setOrdersInfoNum(int ordersInfoNum) {
        this.ordersInfoNum = ordersInfoNum;
    }

    public String getOrdersInfoTime() {
        return ordersInfoTime;
    }

    public void setOrdersInfoTime(String ordersInfoTime) {
        this.ordersInfoTime = ordersInfoTime;
    }

    public String getOrdersInfoModel() {
        return ordersInfoModel;
    }

    public void setOrdersInfoModel(String ordersInfoModel) {
        this.ordersInfoModel = ordersInfoModel;
    }
}
