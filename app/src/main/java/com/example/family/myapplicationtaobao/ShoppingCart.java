package com.example.family.myapplicationtaobao;

/**
 * Created by Family on 2019/6/6.
 */

//购物车实体类
public class ShoppingCart {
    private int shoppingCartld; //购物车id
    private int goodsId;//商品id
    private String che_dan;//购物车单选框
    private String shoppingCartModel; //商品型号
    private int shoppingCartNum; //商品数量
    private ShangpingLB goods;

    public int getShoppingCartld() {
        return shoppingCartld;
    }

    public void setShoppingCartld(int shoppingCartld) {
        this.shoppingCartld = shoppingCartld;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getChe_dan() {
        return che_dan;
    }

    public void setChe_dan(String che_dan) {
        this.che_dan = che_dan;
    }

    public String getShoppingCartModel() {
        return shoppingCartModel;
    }

    public void setShoppingCartModel(String shoppingCartModel) {
        this.shoppingCartModel = shoppingCartModel;
    }

    public int getShoppingCartNum() {
        return shoppingCartNum;
    }

    public void setShoppingCartNum(int shoppingCartNum) {
        this.shoppingCartNum = shoppingCartNum;
    }

    public ShangpingLB getGoods() {
        return goods;
    }

    public void setGoods(ShangpingLB goods) {
        this.goods = goods;
    }
}
