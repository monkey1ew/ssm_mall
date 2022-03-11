package com.fqh.service.impl;

import com.fqh.bean.Orders;
import com.fqh.bean.User;
import com.fqh.dao.UserMapper;
import com.fqh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 海盗狗
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUserById(Integer id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public User checkUser(String username) {
        return userMapper.checkUser(username);
    }

    @Override
    public List<Orders> getUserAndOrders(String username) {
        return userMapper.getUserAndOrders(username);
    }

    @Override
    public User getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    @Override
    public Double getUserAccount(String username) {
        return userMapper.getUserAccount(username);
    }

    @Override
    public int debitAccount(String username, Double money) {
        return userMapper.debitAccount(username, money);
    }
}
