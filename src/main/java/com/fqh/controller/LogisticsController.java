package com.fqh.controller;

import com.fqh.bean.Goods;
import com.fqh.bean.Logistics;
import com.fqh.service.LogisticsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

/**
 * @author 海盗狗
 * @version 1.0
 */
@Controller
public class LogisticsController {


    @Autowired
    private LogisticsService logisticsService;


    @RequestMapping(value = "/logistics/{type}", method = RequestMethod.GET)
    public String showLogistics(@RequestParam(value = "pn", defaultValue = "1")Integer pn,
                                @PathVariable(value = "type", required = false) String type,
                                HttpSession session, Model model) {

        List<Logistics> logisticsList = null;
        String username = (String) session.getAttribute("username");
        type = type.toLowerCase();
        if (type.equalsIgnoreCase("DELIVERED")) {
            logisticsList = logisticsService.getLogisticsByReceiver(username, type);
        }else if (type.equalsIgnoreCase("TRANSIT")) {
            logisticsList = logisticsService.getLogisticsByReceiver(username, type);
        }else if (type.equalsIgnoreCase("RECEIPT")) {
            logisticsList = logisticsService.getLogisticsByReceiver(username, type);
        }else if (type.equalsIgnoreCase("RECEIVED")) {
            logisticsList = logisticsService.getLogisticsByReceiver(username, type);
        }else if (type.equalsIgnoreCase("COMMENT")) {
            logisticsList = logisticsService.getLogisticsByReceiver(username, type);
        }else {
            logisticsList = logisticsService.getLogisticsByReceiver(username, null);
        }
        PageInfo<Logistics> pageInfo = new PageInfo<>(logisticsList, 5);
        model.addAttribute("pageInfo", pageInfo);

        return "logistics";
    }
}
