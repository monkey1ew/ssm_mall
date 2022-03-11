package com.fqh.service;

import com.fqh.bean.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 海盗狗
 * @version 1.0
 */

public interface CartService {
    public int addItem(Cart cart);

    public int updateItem(Double totalPrice, Integer goodsNmb, @Param("gName")String goodsName);

    public int deleteItem(@Param("gName")String goodsName, Integer id, @Param("cName")String cartName);

    public List<Cart> getCart(@Param("cName") String cartName);

    public Cart getItem(String cartName, String goodsName, @Param("id")Integer id);

    public int getCartCount(@Param("cName") String cartName);
}
