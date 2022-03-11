package com.fqh.service;

import com.fqh.bean.Logistics;
import com.fqh.bean.Orders;
import com.fqh.enum_.OrderStatus;
import com.fqh.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author 海盗狗
 * @version 1.0
 */
@Service
public class TaskService {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private LogisticsService logisticsService;

    private String username;

//    订单超时任务
    public void setTimeOut() {
        List<Orders> ordersList = ordersService.findAllOrders(null, null);
        System.out.println("执行定时任务............");
        for (Orders orders : ordersList) {
            double minute = DateUtils.calGapMinute(null, orders.getCreateTime());
            if (minute > 30 && !orders.getOrderStatus().equals("COMPLETED")) {
                ordersService.updateOrder(null, OrderStatus.REVOKED.name(), orders.getOrderNumber());
            }
        }
    }

//    定时清理未完成的订单 1-60-6
    public void setTimeClean() {
        List<Orders> ordersList = ordersService.findAllOrders(null, null);
        for (Orders orders : ordersList) {
            if (orders.getOrderStatus().equals("REVOKE")) {
                ordersService.deleteOrder(orders.getOrderNumber());
            }
        }
    }

//    更新物流任务
    public void updateLogistics() {
        List<Logistics> logisticsList = logisticsService.getAllLogistics();
        for (Logistics logistics : logisticsList) {
            if (logistics.getlStatus().equals("DELIVERED")) {
                logisticsService.updateLogistics(logistics.getlNumber(), "TRANSIT");
            }else if (logistics.getlStatus().equals("TRANSIT")) {
                logisticsService.updateLogistics(logistics.getlNumber(), "RECEIPT");
            }else if (logistics.getlStatus().equals("RECEIPT")) {
                logisticsService.updateLogistics(logistics.getlNumber(), "RECEIVED");
            }else if (logistics.getlStatus().equals("RECEIVED")) {
                logisticsService.updateLogistics(logistics.getlNumber(), "COMMENT");
            }
        }
    }
}
