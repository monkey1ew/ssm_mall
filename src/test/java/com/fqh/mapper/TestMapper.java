package com.fqh.mapper;

import com.fqh.bean.Cart;
import com.fqh.bean.Goods;
import com.fqh.bean.Orders;
import com.fqh.bean.User;
import com.fqh.dao.CartMapper;
import com.fqh.dao.GoodsMapper;
import com.fqh.dao.OrdersMapper;
import com.fqh.dao.UserMapper;
import com.fqh.enum_.GoodsType;
import com.fqh.enum_.OrderStatus;
import com.fqh.utils.DateUtils;
import com.fqh.utils.ParamUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author 海盗狗
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-application.xml"})
public class TestMapper {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private CartMapper cartMapper;

    @Test
    public void testUser() {
//        用户查询自己的订单
        List<Orders> ordersList = userMapper.getUserAndOrders("jack");
        for (Orders order : ordersList) {
            System.out.println(order);
        }
    }

    @Test
    public void testGoods() {

//        List<Goods> goodsList = new ArrayList<>();
//        goodsList.add(new Goods(null, UUID.randomUUID().toString().substring(0, 11),
//                "算法导论", GoodsType.BOOK.name(), 109.00, "机械工业出版社"));
//        goodsList.add(new Goods(null, UUID.randomUUID().toString().substring(0, 11),
//                "自己", GoodsType.CLOTHES.name(), 99.00, "ADIDAS公司"));
//        goodsList.add(new Goods(null, UUID.randomUUID().toString().substring(0, 11),
//                "iphone13proMax", GoodsType.PHONE.name(), 13999.00, "美国苹果公司"));
//        goodsList.add(new Goods(null, UUID.randomUUID().toString().substring(0, 11),
//                "java核心技术卷II", GoodsType.BOOK.name(), 89.00, "机械工业出版社"));
//
//        goodsMapper.addGoodsList(goodsList);
    }


//    @Test
//    public void testOrders() {
////        测试没声明事务 jack少了两次钱
////        userMapper.debitAccount("jack", 89.00);
////        ordersMapper.updateOrder(null, OrderStatus.COMPLETED.name(), "28df133cvb1cdv4375vb2d");
//        List<Orders> orders = ordersMapper.findAllOrders(null);
//        for (Orders order : orders) {
//            System.out.println(order);
//        }
//    }

    @Test
    public void testCart() {
        Cart cart = new Cart(null, "jack@qq.com", ParamUtils.createUid(), "李宁外套");

        Cart item = cartMapper.getItem(cart.getCartName(), cart.getGoodsName(), null);
        Double price = goodsMapper.getGoodsPrice(cart.getGoodsName());
        if (item != null) { //购物车有这个商品 更改totalPrice 和 goodsNmb
            cartMapper.updateItem(item.getTotalPrice() + price, item.getGoodsNmb() + 1, item.getGoodsName());
        }else {
            cart.setTotalPrice(price);
            cart.setGoodsNmb(1);
            cartMapper.addItem(cart);
        }
    }
}
