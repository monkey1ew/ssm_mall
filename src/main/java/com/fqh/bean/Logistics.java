package com.fqh.bean;

import java.io.Serializable;

/**
 * @author 海盗狗
 * @version 1.0
 */
//物流表
public class Logistics implements Serializable {

    private Integer id;
    private String lNumber;
    private String lCompany;
    private String lGoods;
    private String lStatus;
    private String lAddress;
    private String lReceiver;
    private Goods goods;

    public Logistics() {
    }

    public Logistics(Integer id, String lNumber, String lCompany, String lGoods, String lStatus, String lAddress, String lReceiver) {
        this.id = id;
        this.lNumber = lNumber;
        this.lCompany = lCompany;
        this.lGoods = lGoods;
        this.lStatus = lStatus;
        this.lAddress = lAddress;
        this.lReceiver = lReceiver;
    }

    public Logistics(Integer id, String lNumber, String lCompany, String lGoods) {
        this.id = id;
        this.lNumber = lNumber;
        this.lCompany = lCompany;
        this.lGoods = lGoods;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getlNumber() {
        return lNumber;
    }

    public void setlNumber(String lNumber) {
        this.lNumber = lNumber;
    }

    public String getlCompany() {
        return lCompany;
    }

    public void setlCompany(String lCompany) {
        this.lCompany = lCompany;
    }

    public String getlGoods() {
        return lGoods;
    }

    public void setlGoods(String lGoods) {
        this.lGoods = lGoods;
    }

    public String getlStatus() {
        return lStatus;
    }

    public void setlStatus(String lStatus) {
        this.lStatus = lStatus;
    }

    public String getlAddress() {
        return lAddress;
    }

    public void setlAddress(String lAddress) {
        this.lAddress = lAddress;
    }

    public String getlReceiver() {
        return lReceiver;
    }

    public void setlReceiver(String lReceiver) {
        this.lReceiver = lReceiver;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Logistics{" +
                "id=" + id +
                ", lNumber='" + lNumber + '\'' +
                ", lCompany='" + lCompany + '\'' +
                ", lGoods='" + lGoods + '\'' +
                ", lStatus='" + lStatus + '\'' +
                ", lAddress='" + lAddress + '\'' +
                ", lReceiver='" + lReceiver + '\'' +
                ", goods=" + goods +
                '}';
    }
}
