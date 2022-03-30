package com.fqh.controller;

import com.fqh.bean.Cart;
import com.fqh.bean.Goods;
import com.fqh.bean.Orders;
import com.fqh.service.CartService;
import com.fqh.service.GoodsService;
import com.fqh.service.UserService;
import com.fqh.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.AlgorithmConstraints;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author 海盗狗
 * @version 1.0
 */
@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(@RequestBody Map<String, Object> map, HttpSession session) {
        Cart cart = new Cart();
        String cartName = session.getAttribute("username") + "@qq.com";
        cart.setCartName(cartName);

        Goods goods = goodsService.getGoodsByName((String) map.get("goodsName"));
        cart.setGoodsName(goods.getGoodsName());
        cart.setBusiness(goods.getBusiness());
//        拿到nums判断是否是添加多个
        Integer nums = 1;
        if ((String) map.get("goodsNmb") != null) {
            nums = Integer.parseInt((String) map.get("goodsNmb"));
        }
        Cart item = cartService.getItem(cart.getCartName(), cart.getGoodsName(), null);
        Double price = goods.getGoodsPrice();
        if (item != null) { //购物车有这个商品 更新totalPrice 和 goodsNmb
            if (nums != null) {
                cartService.updateItem(item.getTotalPrice() + price, item.getGoodsNmb() + nums, item.getGoodsName());
            }else {
                cartService.updateItem(item.getTotalPrice() + price, item.getGoodsNmb() + 1, item.getGoodsName());
            }
        }else {
            cart.setTotalPrice(price);
            cart.setGoodsNmb(1);
            cartService.addItem(cart);
        }
        //        重置session域中的购物车商品数量
        session.setAttribute("cartNums", cartService.getCartCount(cartName));
        return Result.success();
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String orders(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                         HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        PageHelper.startPage(pn, 16);
//        查询前调用 pn:页码, pageSize:每页几条记录
        List<Cart> cartList = cartService.getCart(username + "@qq.com");

//        pageInfo 包装查询后的结果
//        navigatePage:连续显示的额页数
        PageInfo<Cart> pageInfo = new PageInfo<>(cartList, 5);
        model.addAttribute("pageInfo", pageInfo);
        session.setAttribute("account", userService.getUserAccount(username));
        return "cart";
    }

    @RequestMapping(value = "/payCart", method = RequestMethod.POST)
    @ResponseBody
    public Result payCart(@RequestBody Map<String, Object> map, HttpSession session) {
        String id = (String) map.get("ID");
        String username = (String) session.getAttribute("username");
        Cart item = cartService.getItem(null, null, Integer.parseInt(id));
        Double money = userService.getUserAccount(username);
        System.out.println(money);
        if (item.getTotalPrice() > money) {
            Result result = Result.failed();
            result.setMessage("支付失败, 余额不足");
            return result;
        }
        userService.debitAccount(username, item.getTotalPrice());
        cartService.deleteItem(null, Integer.parseInt(id), null);
        return Result.success();
    }

    @RequestMapping(value = "/delCart", method = RequestMethod.POST)
    @ResponseBody
    public Result delCart(@RequestBody Map<String, Object> map) {
        String id = (String) map.get("ID");
        cartService.deleteItem(null, Integer.parseInt(id), null);
        return Result.success();
    }

    @RequestMapping(value = "/payAll", method = RequestMethod.POST)
    @ResponseBody
    public Result payAll(HttpSession session) {
        String username = (String) session.getAttribute("username");
        List<Cart> cartList = cartService.getCart(username + "@qq.com");
        Double account = userService.getUserAccount(username);
        double total = 0;
        Result result = new Result();
        if (cartList.isEmpty()) {
            result.setCode(200);
            result.setMessage("你的购物车为空");
            return result;
        }
        for (Cart cart : cartList) {
            total += cart.getTotalPrice();
        }
        if (account < total) {
            result.setCode(200);
            result.setMessage("余额不足,支付失败");
            return result;
        }
        userService.debitAccount(username, total);
        cartService.deleteItem(null, null, username + "@qq.com");
        return Result.success();
    }

    @RequestMapping(value = "/delAll", method = RequestMethod.POST)
    @ResponseBody
    public Result delAll(HttpSession session) {
        String cartName = (String) session.getAttribute("username");
        int rows = cartService.deleteItem(null, null, cartName + "@qq.com");
        if (rows > 0) {
            return Result.success();
        }
        Result result = new Result();
        result.setCode(200);
        result.setMessage("删除失败, 购物车为空");
        return result;
    }

}
