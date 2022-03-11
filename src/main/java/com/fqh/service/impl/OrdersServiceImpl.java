package com.fqh.service.impl;

import com.fqh.bean.Orders;
import com.fqh.dao.OrdersMapper;
import com.fqh.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 海盗狗
 * @version 1.0
 */
@Service
public class OrdersServiceImpl implements OrdersService {


    @Autowired
    private OrdersMapper ordersMapper;


    @Override
    public int addOrder(Orders orders) {
        return ordersMapper.addOrder(orders);
    }

    @Override
    public int deleteOrder(String orderNumber) {
        return ordersMapper.deleteOrder(orderNumber);
    }

    @Override
    public List<Orders> findAllOrders(String name, String rule) {
        return ordersMapper.findAllOrders(name, rule);
    }

    @Override
    public int updateOrder(String createPhone, String orderStatus, String orderNumber) {
        return ordersMapper.updateOrder(createPhone, orderStatus, orderNumber);
    }

    @Override
    public Orders findOrderByNumber(String orderNumber) {
        return ordersMapper.findOrderByNumber(orderNumber);
    }
}
