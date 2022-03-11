package com.fqh.enum_;

/**
 * @author 海盗狗
 * @version 1.0
 */
public enum OrderStatus {
//    未支付, 已完成, 已撤销
    UNPAID,COMPLETED,REVOKED;

    private String typeName;

    OrderStatus() {
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
