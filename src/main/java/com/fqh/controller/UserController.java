package com.fqh.controller;

import com.fqh.bean.Goods;
import com.fqh.bean.User;
import com.fqh.service.GoodsService;
import com.fqh.service.TaskService;
import com.fqh.service.UserService;
import com.fqh.utils.DateUtils;
import com.fqh.utils.ParamUtils;
import com.fqh.utils.Result;
import com.github.pagehelper.PageInfo;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author 海盗狗
 * @version 1.0
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password,
                        Model model, HttpSession session) throws UnsupportedEncodingException{
        System.out.println(username + ":" + password);
        String name = ParamUtils.encoding(username);
        User user = userService.checkUser(name);

        if (user != null && password.equals(user.getPassword())) {
            session.setAttribute("username", name);
//            D:\mall\src\main\webapp\static\img\picture\鸡.png
            session.setAttribute("userImgPath", user.getPicturePath().substring(23));
            return "redirect:/home"; // 重定向到/home请求被Thymeleaf解析
        }else {
            model.addAttribute("msg", "账号或者密码错误");
            return "login";
        }
    }

    @RequestMapping(value = "/")
    public String index() {
        return "login";
    }

    @RequestMapping(value = "/home")
    public String toHome(Model model, HttpSession session) {
        List<Goods> goodsList = goodsService.getAllGoods();
//        Collections.sort(goodsList, ((o1, o2) -> (o2.getGoodsSold() - o1.getGoodsSold())));
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList, 5);
        model.addAttribute("pageInfo", pageInfo);

        String username = (String) session.getAttribute("username");
        if (username != null && username.equals("admin")) {
            return "ahome";
        }
        return "home";
    }

    @RequestMapping(value = "/rejisUser")
    public String rejis() {
        return "rejis";
    }

    @RequestMapping(value = "/rejisTo")
    public String rejisUser(String username, String password, String email,
                            String address, String phone, Model model,
                            HttpSession session) throws IOException {
        if (username.length() > 0 && password.length() > 0) {
//            String name = ParamUtils.encoding(username);
            String name = username;
            User user = userService.checkUser(name);
            String path = (String) session.getAttribute("userImgPath");

            if (user == null) {
                User u = new User();
                u.setUsername(name);
                u.setPassword(password);
                u.setEmail(email);
//                u.setAddress(ParamUtils.encoding(address));
                u.setAddress(address);
                u.setPhone(phone);
                u.setPicturePath(path);
                userService.insert(u);
                session.setAttribute("rejisMsg", "注册成功, 请登录");
                return "redirect:/";
            }
            model.addAttribute("msg", "用户名存在!");
        }else {
            model.addAttribute("msg", "账号和密码不能为空");
        }
//        注册失败删除session中保存的对象路径
        session.removeAttribute("userImgPath");
        return "rejis";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "redirect:/";
    }

    @GetMapping(value = "/user/info")
    public String goToPage() {
        return "info";
    }

//    头像上传
    @RequestMapping(value = "/file/upload")
    @ResponseBody
    public Result fileUpload(@RequestParam("file") MultipartFile file,
                             HttpSession session) throws IOException {
        String filePath = "D:\\mall\\src\\main\\webapp\\static\\img\\picture";
        String fileName = file.getOriginalFilename();
        File targetFile = new File(filePath, fileName);
        file.transferTo(targetFile);
        session.setAttribute("userImgPath", filePath + "\\" + fileName);
        System.out.println("上传成功");
        return Result.success();
    }
}
