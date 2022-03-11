package com.fqh.service.impl;

import com.fqh.bean.Goods;
import com.fqh.dao.GoodsMapper;
import com.fqh.service.GoodsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 海盗狗
 * @version 1.0
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> findByType(String goodsType) {
        return goodsMapper.findByType(goodsType);
    }

    @Override
    public int addGoods(Goods goods) {
        return goodsMapper.addGoods(goods);
    }

    @Override
    public int addGoodsList(@Param("goods")List<Goods> goods) {
        return goodsMapper.addGoodsList(goods);
    }


    @Override
    public List<Goods> getAllGoods() {
        return goodsMapper.getAllGoods();
    }

    @Override
    public int updateGoods(Goods goods) {
        return goodsMapper.updateGoods(goods);
    }

    @Override
    public Double getGoodsPrice(String goodsName) {
        return goodsMapper.getGoodsPrice(goodsName);
    }

    @Override
    public int deleteGoods(String uId) {
        return goodsMapper.deleteGoods(uId);
    }

    @Override
    public List<Goods> searchGoods(String goodsName) {
        return goodsMapper.searchGoods(goodsName);
    }

    @Override
    public Goods getGoodsByName(String goodsName) {
        return goodsMapper.getGoodsByName(goodsName);
    }

    @Override
    public Goods getGoodsById(Integer id) {
        return goodsMapper.getGoodsById(id);
    }

}
