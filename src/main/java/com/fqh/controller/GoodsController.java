package com.fqh.controller;

import com.fqh.bean.Cart;
import com.fqh.bean.Goods;
import com.fqh.enum_.GoodsType;
import com.fqh.service.CartService;
import com.fqh.service.GoodsService;
import com.fqh.service.UserService;
import com.fqh.utils.ParamUtils;
import com.fqh.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author 海盗狗
 * @version 1.0
 */
@Controller
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

//    查询商品数据 (分页)
    @RequestMapping(value = "/goods/{sort}", method = RequestMethod.GET)
    public String getGoods(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                           @PathVariable("sort") String sort,
                           Model model, HttpSession session) {
//        引入PageHelper
//        在查询前调用 pn:页码, pageSize:每页几条记录
//        PageHelper.startPage(pn, 5);
        List<Goods> goodsList = goodsService.getAllGoods();
        if (sort.equals("desc")) {
            Collections.sort(goodsList, ((o1, o2) -> (int) (o1.getGoodsPrice() -  o2.getGoodsPrice())));
        }else if (sort.equals("esc")) {
            Collections.sort(goodsList, ((o1, o2) -> (int) (o2.getGoodsPrice() - o1.getGoodsPrice())));
        }else if (sort.equals("sell")) {
            Collections.sort(goodsList, ((o1, o2) -> (int) (o2.getGoodsSold() - o1.getGoodsSold())));
        }
//        使用pageInfo 包装查询后的结果
//        navigatePage:连续显示的额页数
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList, 5);
        model.addAttribute("pageInfo", pageInfo);

        String username = (String) session.getAttribute("username");
        if (username != null && username.equals("admin")) {
            System.out.println("返回home");
            return "ahome";
        }
        return "home";
    }

    @RequestMapping(value = "/addGoods", method = RequestMethod.POST)
    @ResponseBody
    public Result addGoods(@RequestBody Map<String, Object> map) {
        System.out.println("addGoods.....................");
        Goods goods = new Goods();
        goods.setuId(ParamUtils.createUid());
        goods.setGoodsName((String) map.get("goodsName"));
        goods.setGoodsType((String) map.get("goodsType"));
        goods.setGoodsPrice(Double.parseDouble((String) map.get("goodsPrice")));
        goods.setBusiness((String) map.get("business"));
        goodsService.addGoods(goods);
        return Result.success();
    }

    @RequestMapping(value = "/delGoods", method = RequestMethod.POST)
    @ResponseBody
    public Result delGoods(@RequestBody Map<String, Object> map) {
        goodsService.deleteGoods((String) map.get("uid"));
        return Result.success();
    }

    @RequestMapping(value = "/updateGoods", method = RequestMethod.POST)
    @ResponseBody
    public Result updateGoods(@RequestBody Goods goods) {
        String type = goods.getGoodsType();
        if (type.equals("手机")) {
            goods.setGoodsType(GoodsType.PHONE.name());
        }else if (type.equals("服饰")) {
            goods.setGoodsType(GoodsType.CLOTHES.name());
        }else if (type.equals("食品")) {
            goods.setGoodsType(GoodsType.FOOD.name());
        }else if (type.equals("书籍")) {
            goods.setGoodsType(GoodsType.BOOK.name());
        }
        goodsService.updateGoods(goods);
        return Result.success();
    }

    @PostMapping(value = "/search")
    public String search(@RequestParam("gName")String goodsName, Model model) throws UnsupportedEncodingException {
        goodsName = ParamUtils.encoding(goodsName);
        List<Goods> goodsList = goodsService.searchGoods(goodsName);

        Collections.sort(goodsList, ((o1, o2) -> (o2.getGoodsSold() - o1.getGoodsSold())));
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList, 5);
        model.addAttribute("pageInfo", pageInfo);
        return "home";
    }

    @GetMapping(value = "/goodsinfo/{id}")
    public String goToGoodsPage(@PathVariable("id") Integer id,
                                Model model, HttpSession session) {
        Goods goods = goodsService.getGoodsById(id);
        model.addAttribute("goodsInfo", goods);
        int count = cartService.getCartCount(session.getAttribute("username") + "@qq.com");
        System.out.println(count);
        session.setAttribute("cartNums", count);
        return "goodsinfo";
    }
}
