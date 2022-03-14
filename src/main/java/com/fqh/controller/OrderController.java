package com.fqh.controller;

import com.fqh.bean.Goods;
import com.fqh.bean.Logistics;
import com.fqh.bean.Orders;
import com.fqh.bean.User;
import com.fqh.enum_.LogisticsStatus;
import com.fqh.enum_.OrderStatus;
import com.fqh.service.*;
import com.fqh.utils.DateUtils;
import com.fqh.utils.ParamUtils;
import com.fqh.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.net.httpserver.Authenticator;
import com.sun.org.apache.bcel.internal.generic.ARETURN;
import org.apache.ibatis.annotations.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author 海盗狗
 * @version 1.0
 */
@Controller
public class OrderController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private LogisticsService logisticsService;

    private Result result;

    @RequestMapping(value = "/pOrder", method = RequestMethod.POST)
    @ResponseBody
    public Result pOrder(@RequestBody Map<String, Object> map, HttpSession session) {
        Orders orders = new Orders();
        String username = (String) session.getAttribute("username");

        User user = userService.getUserByName(username);
        orders.setCreateUser(username + "@qq.com");
        orders.setCreatePhone(user.getPhone());
        orders.setCreateTime(DateUtils.formatDate());
        orders.setOrderNumber(ParamUtils.createUid());
        orders.setOrderStatus(OrderStatus.UNPAID.name());
        orders.setGoodsInfo((String) map.get("goodsName"));
        orders.setOrderPrice(Double.parseDouble((String) map.get("goodsPrice")));
        orders.setBusiness((String) map.get("business"));
        orders.setoAddress(user.getAddress());

        ordersService.addOrder(orders);
        return Result.success();
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    @ResponseBody
    public Result pay(@RequestBody Map<String, Object> map, HttpSession session) {
        String username = (String) session.getAttribute("username");
        Double account = userService.getUserAccount(username);
        Double orderPrice = Double.parseDouble((String) map.get("orderPrice"));
        Orders order = ordersService.findOrderByNumber((String) map.get("orderNumber"));
        if (order.getOrderStatus().equals("COMPLETED") || order.getOrderStatus().equals("REVOKED")) {
            Result result = Result.failed();
            result.setMessage("支付失败, 订单已完成或者撤销");
            return result;
        }
        if (account < orderPrice) {
//            发送json数据状态码给前端 余额不足
            Result result = Result.failed();
            result.setMessage("支付失败, 余额不足");
            return result;
        }
//        扣钱并更新订单状态
        goodsService.updateGoods(new Goods(null, null, order.getBusiness(), 1));
        Logistics logistics = new Logistics(null, ParamUtils.createUid(), "邮政快递", order.getGoodsInfo());
        User user = userService.getUserByName(username);
        logistics.setlStatus(LogisticsStatus.DELIVERED.name());
        logistics.setlAddress(user.getAddress());
        logistics.setlReceiver(username);
        logisticsService.addLogistics(logistics);


        userService.debitAccount(username, orderPrice);
        ordersService.updateOrder(null, OrderStatus.COMPLETED.name(), (String) map.get("orderNumber"));
        return Result.success();
    }


    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String orders(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                         HttpSession session, Model model) {
        PageHelper.startPage(pn, 8);
        //        在查询前调用 pn:页码, pageSize:每页几条记录 limit
        List<Orders> orders = ordersService.findAllOrders(session.getAttribute("username") + "@qq.com", null);
//        封装了详细的信息, navigatePage:连续显示的额页数

        PageInfo<Orders> pageInfo = new PageInfo<>(orders, 5);
        session.setAttribute("account", userService.getUserAccount((String) session.getAttribute("username")));
        model.addAttribute("pageInfo", pageInfo);

        return "order";
    }

    @RequestMapping(value = "/revoke", method = RequestMethod.POST)
    @ResponseBody
    public Result revoke(@RequestBody Map<String, Object> map) {
        String oNumber = (String) map.get("oNumber");
        Orders order = ordersService.findOrderByNumber(oNumber);
        if (order.getOrderStatus().equals(OrderStatus.UNPAID.name())) {
            ordersService.updateOrder((String) map.get("oPhone"), OrderStatus.REVOKED.name(), oNumber);
            return Result.success();
        }
        Result result = Result.failed();
        result.setMessage("撤销失败,订单已完成");
        return result;

    }

    @RequestMapping(value = "/orders/{sort}", method = RequestMethod.GET)
    public String orderByTime(@PathVariable("sort") String sort,
                              @RequestParam(value = "pn", defaultValue = "1") Integer pn,
                         HttpSession session, Model model) {
        PageHelper.startPage(pn, 8);

        List<Orders> orders = ordersService.findAllOrders(session.getAttribute("username") + "@qq.com", sort);
//
//        if (sort != null) {
//            if (sort.equals("up")) {
//                System.out.println("升序.........");
//                Collections.sort(orders, ((o1, o2) -> {
//                    return (int) DateUtils.calGapMinute(o2.getCreateTime(), o1.getCreateTime());
//                }));
//            }else {
//                System.out.println("降序......................");
//                Collections.sort(orders, ((o1, o2) -> {
//                    return (int) (DateUtils.calGapMinute(o2.getCreateTime(), o1.getCreateTime()));
//                }));
//            }
//        }
        PageInfo<Orders> pageInfo = new PageInfo<>(orders, 5);
        session.setAttribute("account", userService.getUserAccount((String) session.getAttribute("username")));
        model.addAttribute("pageInfo", pageInfo);

        return "order";
    }

    @PostMapping(value = "/payInfo")
    @ResponseBody
    public Result getPayInfo(@RequestBody Map<String, Object> map,
                             HttpSession session) {
        String username = (String) session.getAttribute("username");
        String address = "";
        String gName = (String) map.get("gName");
        Goods goods = goodsService.getGoodsByName(gName);

        User user = userService.getUserByName(username);
        if (map.get("address") == null) {
            address = user.getAddress();
        }else {
            address = (String) map.get("address");
        }

        Orders orders = new Orders();
        orders.setCreateUser(username + "@qq.com");
        orders.setCreatePhone((String) map.get("phone"));
        orders.setCreateTime(DateUtils.formatDate());
        orders.setOrderNumber(ParamUtils.createUid());
        orders.setOrderStatus(OrderStatus.COMPLETED.name());
        orders.setOrderPrice(Double.parseDouble((String) map.get("gTotalPrice")));
        orders.setGoodsInfo(gName);
        orders.setBusiness(goods.getBusiness());
        orders.setoAddress(address);
        ordersService.addOrder(orders);

        Logistics logistics = new Logistics();
        logistics.setlNumber(ParamUtils.createUid());
        logistics.setlCompany("邮政快递");
        logistics.setlGoods(gName);
        logistics.setlStatus(LogisticsStatus.DELIVERED.name());
        logistics.setlAddress(address);
        logistics.setlReceiver(username);
        logisticsService.addLogistics(logistics);

        return Result.success();
    }
}
