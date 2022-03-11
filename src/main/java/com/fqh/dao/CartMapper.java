package com.fqh.dao;

import com.fqh.bean.Cart;
import com.fqh.bean.Goods;
import com.sun.tracing.dtrace.ProviderAttributes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 海盗狗
 * @version 1.0
 */
public interface CartMapper {

    public int addItem(Cart cart);

    public int updateItem(@Param("tPrice") Double totalPrice, @Param("gNmb") Integer goodsNmb, @Param("gName")String goodsName);

    public int deleteItem(@Param("gName")String goodsName, @Param("id") Integer id, @Param("cName")String cartName);

    public List<Cart> getCart(@Param("cName") String cartName);

    public Cart getItem(@Param("cName")String cartName,
                        @Param("gName")String goodsName,
                        @Param("id")Integer id);

    public int getCartCount(@Param("cName") String cartName);
}
