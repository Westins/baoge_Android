package com.example.family.myapplicationtaobao;

import java.util.Date;
import java.util.List;

public class Shops {
	private int shopsId, shopsSalesVolume,UserId,shopState;
	private String shopsName, shopsLogo, shopsImgIntroduce, shopsVideoIntroduce,shopsTextIntroduce;
	private double shopsSales;
	private Date shopsTime;
	private List<ShangpingLB> goods;
	private UserInfo userinfo;

	public int getShopsId() {
		return shopsId;
	}

	public void setShopsId(int shopsId) {
		this.shopsId = shopsId;
	}

	public int getShopsSalesVolume() {
		return shopsSalesVolume;
	}

	public void setShopsSalesVolume(int shopsSalesVolume) {
		this.shopsSalesVolume = shopsSalesVolume;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public int getShopState() {
		return shopState;
	}

	public void setShopState(int shopState) {
		this.shopState = shopState;
	}

	public String getShopsName() {
		return shopsName;
	}

	public void setShopsName(String shopsName) {
		this.shopsName = shopsName;
	}

	public String getShopsLogo() {
		return shopsLogo;
	}

	public void setShopsLogo(String shopsLogo) {
		this.shopsLogo = shopsLogo;
	}

	public String getShopsImgIntroduce() {
		return shopsImgIntroduce;
	}

	public void setShopsImgIntroduce(String shopsImgIntroduce) {
		this.shopsImgIntroduce = shopsImgIntroduce;
	}

	public String getShopsVideoIntroduce() {
		return shopsVideoIntroduce;
	}

	public void setShopsVideoIntroduce(String shopsVideoIntroduce) {
		this.shopsVideoIntroduce = shopsVideoIntroduce;
	}

	public String getShopsTextIntroduce() {
		return shopsTextIntroduce;
	}

	public void setShopsTextIntroduce(String shopsTextIntroduce) {
		this.shopsTextIntroduce = shopsTextIntroduce;
	}

	public double getShopsSales() {
		return shopsSales;
	}

	public void setShopsSales(double shopsSales) {
		this.shopsSales = shopsSales;
	}

	public Date getShopsTime() {
		return shopsTime;
	}

	public void setShopsTime(Date shopsTime) {
		this.shopsTime = shopsTime;
	}

	public List<ShangpingLB> getGoods() {
		return goods;
	}

	public void setGoods(List<ShangpingLB> goods) {
		this.goods = goods;
	}

	public UserInfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}
}
