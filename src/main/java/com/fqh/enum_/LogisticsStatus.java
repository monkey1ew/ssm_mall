package com.fqh.enum_;

/**
 * @author 海盗狗
 * @version 1.0
 */
public enum LogisticsStatus {
//    待发货,运输中,待收货,已收货,待评价
    DELIVERED,TRANSIT,RECEIPT,RECEIVED,COMMENT;

    private String typeName;

    LogisticsStatus() {

    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
