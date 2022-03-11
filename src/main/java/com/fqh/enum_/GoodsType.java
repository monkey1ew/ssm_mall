package com.fqh.enum_;

/**
 * @author 海盗狗
 * @version 1.0
 */
public enum GoodsType {

    PHONE,FOOD,CLOTHES,BOOK;

    private String typeName;

    GoodsType() {
    }

    public String getTypeName() {

        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
