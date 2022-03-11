package com.fqh.dao;

import com.fqh.bean.Orders;
import com.fqh.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserMapper {

    public int insert(User user);

    public List<User> findAll();

    public int deleteUserById(Integer id);

    public User checkUser(String username);

    public int updateUser(User user);

    public List<Orders> getUserAndOrders(String username);

    public User getUserByName(String username);

    public Double getUserAccount(@Param("name") String username);

    public int debitAccount(@Param("name") String username, @Param("money") Double money);

}