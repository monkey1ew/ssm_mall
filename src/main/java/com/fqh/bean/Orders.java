package com.fqh.bean;

import java.io.Serializable;

/**
 * @author 海盗狗
 * @version 1.0
 */
public class Orders implements Serializable {
    private Integer id;
    private String createUser;
    private String createPhone;
    private String createTime;
    private String orderNumber;
    private String orderStatus;
    private String goodsInfo;
    private Double orderPrice;
    private String business;
    private String oAddress; //订单地址

    public Orders() {
    }

    public Orders(Integer id, String createUser, String createPhone, String createTime, String orderNumber, String orderStatus, String goodsInfo, Double orderPrice, String business) {
        this.id = id;
        this.createUser = createUser;
        this.createPhone = createPhone;
        this.createTime = createTime;
        this.orderNumber = orderNumber;
        this.orderStatus = orderStatus;
        this.goodsInfo = goodsInfo;
        this.orderPrice = orderPrice;
    }

    public Orders(Integer id, String createUser, String createPhone,
                  String createTime, String orderNumber, String orderStatus, String goodsInfo, String business) {
        this.id = id;
        this.createUser = createUser;
        this.createPhone = createPhone;
        this.createTime = createTime;
        this.orderNumber = orderNumber;
        this.orderStatus = orderStatus;
        this.goodsInfo = goodsInfo;
        this.business =  business;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreatePhone() {
        return createPhone;
    }

    public void setCreatePhone(String createPhone) {
        this.createPhone = createPhone;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(String goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getoAddress() {
        return oAddress;
    }

    public void setoAddress(String oAddress) {
        this.oAddress = oAddress;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", createUser='" + createUser + '\'' +
                ", createPhone='" + createPhone + '\'' +
                ", createTime='" + createTime + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", goodsInfo='" + goodsInfo + '\'' +
                ", orderPrice=" + orderPrice +
                ", business='" + business + '\'' +
                ", oAddress='" + oAddress + '\'' +
                '}';
    }
}
