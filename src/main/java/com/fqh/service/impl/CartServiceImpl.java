package com.fqh.service.impl;

import com.fqh.bean.Cart;
import com.fqh.dao.CartMapper;
import com.fqh.service.CartService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 海盗狗
 * @version 1.0
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public int addItem(Cart cart) {
        return cartMapper.addItem(cart);
    }

    @Override
    public int updateItem(Double totalPrice, Integer goodsNmb, @Param("gName")String goodsName) {
        return cartMapper.updateItem(totalPrice, goodsNmb, goodsName);
    }

    @Override
    public int deleteItem(String goodsName, Integer id, String cartName) {
        return cartMapper.deleteItem(goodsName, id, cartName);
    }

    @Override
    public List<Cart> getCart(String cartName) {
        return cartMapper.getCart(cartName);
    }

    @Override
    public Cart getItem(String cartName, String goodsName, Integer id) {
        return cartMapper.getItem(cartName, goodsName, id);
    }

    @Override
    public int getCartCount(String cartName) {
        return cartMapper.getCartCount(cartName);
    }
}
