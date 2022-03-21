package com.fqh.bean;

import java.io.Serializable;

/**
 * @author 海盗狗
 * @version 1.0
 */
//评论
public class Comment implements Serializable {

    private Integer id; //主键
    private String userName;
    private String goodsName;
    private String content; //内容
    private String contentTime; //评论时间
    private String stars; //星级
    private Integer likesNumber; //点赞数

    public Comment() {
    }

    public Comment(Integer id, String userName, String goodsName, String content, String contentTime, String stars, Integer likesNumber) {
        this.id = id;
        this.userName = userName;
        this.goodsName = goodsName;
        this.content = content;
        this.contentTime = contentTime;
        this.stars = stars;
        this.likesNumber = likesNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentTime() {
        return contentTime;
    }

    public void setContentTime(String contentTime) {
        this.contentTime = contentTime;
    }

    public Integer getLikesNumber() {
        return likesNumber;
    }

    public void setLikesNumber(Integer likesNumber) {
        this.likesNumber = likesNumber;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", content='" + content + '\'' +
                ", contentTime='" + contentTime + '\'' +
                ", stars='" + stars + '\'' +
                ", likesNumber='" + likesNumber + '\'' +
                '}';
    }
}
