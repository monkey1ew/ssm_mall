package com.fqh.dao;

import com.fqh.bean.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 海盗狗
 * @version 1.0
 */
public interface OrdersMapper {

    public int addOrder(Orders orders);

    public int deleteOrder(String orderNumber);

    public List<Orders> findAllOrders(@Param("createUser") String name, @Param("rule") String rule);

    public int updateOrder(@Param("phone")String createPhone,
                           @Param("status")String orderStatus,
                           @Param("number")String orderNumber);

    public Orders findOrderByNumber(@Param("oNumber") String orderNumber);


}
