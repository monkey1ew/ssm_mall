package com.fqh.service;

import com.fqh.bean.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 海盗狗
 * @version 1.0
 */
public interface GoodsService {

    public List<Goods> findByType(String goodsType);

    public int addGoods(Goods goods);

    public int addGoodsList(@Param("goods")List<Goods> goods);

    public List<Goods> getAllGoods();

    public int updateGoods(Goods goods);

    public Double getGoodsPrice(String goodsName);

    public int deleteGoods(String uId);

    public List<Goods> searchGoods(@Param("gName") String goodsName);

    public Goods getGoodsByName(@Param("gName") String goodsName);

    public Goods getGoodsById(@Param("id") Integer id);


}
