package com.fqh.dao;

import com.fqh.bean.Logistics;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author 海盗狗
 * @version 1.0
 */
public interface LogisticsMapper {


    public List<Logistics> getLogisticsByReceiver(@Param("receiver") String receiverName, @Param("status")String lStatus);

    public int updateLogistics(@Param("number") String lNumber, @Param("status")String lStatus);

    public int addLogistics(Logistics logistics);

    public List<Logistics> getAllLogistics();

}
