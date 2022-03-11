package com.fqh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;


/**
 * @author 海盗狗
 * @version 1.0
 */
@Controller
public class TestController {


    @RequestMapping(value = "/page")
    public String home() {
        return "homePage";
    }
}
