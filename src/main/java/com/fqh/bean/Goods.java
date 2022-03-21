package com.fqh.bean;

import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;

/**
 * @author 海盗狗
 * @version 1.0
 */
public class Goods implements Serializable {
    private Integer id;
    private String uId;
    private String goodsName;
    private String goodsType;
    private Double goodsPrice;
    private String business;
    private Integer goodsSold; //已售出
    private String goodsImg;
    private String description;
    private List<Comment> commentList;

    public Goods() {
    }

    public Goods(Integer id, String uId, String goodsName, String goodsType, Double goodsPrice, String business) {
        this.id = id;
        this.uId = uId;
        this.goodsName = goodsName;
        this.goodsType = goodsType;
        this.goodsPrice = goodsPrice;
        this.business = business;
    }

    public Goods(Integer id, String uId, String business, Integer goodsSold) {
        this.id = id;
        this.uId = uId;
        this.business = business;
        this.goodsSold = goodsSold;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public Integer getGoodsSold() {
        return goodsSold;
    }

    public void setGoodsSold(Integer goodsSold) {
        this.goodsSold = goodsSold;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", uId='" + uId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", business='" + business + '\'' +
                ", goodsSold=" + goodsSold +
                ", goodsImg='" + goodsImg + '\'' +
                ", description='" + description + '\'' +
                ", commentList=" + commentList +
                '}';
    }
}



