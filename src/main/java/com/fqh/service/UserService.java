package com.fqh.service;

import com.fqh.bean.Orders;
import com.fqh.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 海盗狗
 * @version 1.0
 */
public interface UserService {

    int insert(User user);

    List<User> findAll();

    public int updateUser(User user);

    public int deleteUserById(Integer id);

    public User checkUser(String username);

    public List<Orders> getUserAndOrders(String username);

    public User getUserByName(String username);

    public Double getUserAccount(@Param("name") String username);

    public int debitAccount(String username, Double money);

}
