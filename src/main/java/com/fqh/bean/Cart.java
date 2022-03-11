package com.fqh.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author 海盗狗
 * @version 1.0
 */
public class Cart implements Serializable {
    private Integer id;
    private String cartName;
    private String orderName; // 一个种类商品的订单号
    private String goodsName;
    private Double totalPrice;
    private Integer goodsNmb; // 当前商品数量
    private Integer total;
    private String business; //商家
    private Goods goods;

    public Cart() {
    }

    public Cart(Integer id, String cartName, String orderName, String goodsName, Double totalPrice, Integer goodsNmb) {
        this.id = id;
        this.cartName = cartName;
        this.orderName = orderName;
        this.goodsName = goodsName;
        this.totalPrice = totalPrice;
        this.goodsNmb = goodsNmb;
    }

    public Cart(Integer id, String cartName, String orderName, String goodsName) {
        this.id = id;
        this.cartName = cartName;
        this.orderName = orderName;
        this.goodsName = goodsName;
    }

    public Cart(Integer id, String orderName) {
        this.id = id;
        this.orderName = orderName;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsNmb() {
        return goodsNmb;
    }

    public void setGoodsNmb(Integer goodsNmb) {
        this.goodsNmb = goodsNmb;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", cartName='" + cartName + '\'' +
                ", orderName='" + orderName + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", totalPrice=" + totalPrice +
                ", goodsNmb=" + goodsNmb +
                ", total=" + total +
                ", business='" + business + '\'' +
                ", goods=" + goods +
                '}';
    }
}

