package com.fqh.mvc;

import com.fqh.bean.Goods;
import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.portlet.MockEventRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * @author 海盗狗
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring/spring-application.xml",
        "classpath:spring/spring-mvc.xml"})
public class MvcTest {
//    传入springMVC的ioc
    @Autowired
    WebApplicationContext context;
//    虚拟mvc请求, 获取处理结果
    MockMvc mockMvc;

    @Before
    public void initMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testPage() throws Exception{
//        模拟拿到返回值
        MvcResult result =
                mockMvc.perform(MockMvcRequestBuilders.get("/goods").param("pn", "1"))
                        .andReturn();

//        请求成功后, 请求域中会有pageInfo
        MockHttpServletRequest request = result.getRequest();
        PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
        System.out.println("当前页码 : " + pageInfo.getPageNum());
        System.out.println("总页码 : " + pageInfo.getPages());
        System.out.println("总记录数 : " + pageInfo.getTotal());
        System.out.println("在页面连续显示的页码 : ");
        int[] nums = pageInfo.getNavigatepageNums();
        for (int i : nums) {
            System.out.print(" " + i);
        }

//        获取商品数据
        List<Goods> goodsList = pageInfo.getList();
        for (Goods goods : goodsList) {
            System.out.println(goods);
        }
    }

    @Test
    public void testAddGoods() throws Exception{
        MvcResult result =
                mockMvc.perform(MockMvcRequestBuilders.get("/add").param("pn", "1"))
                        .andReturn();
    }

}
