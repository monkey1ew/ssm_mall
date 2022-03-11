package com.fqh.service.impl;

import com.fqh.bean.Logistics;
import com.fqh.dao.LogisticsMapper;
import com.fqh.service.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 海盗狗
 * @version 1.0
 */
@Service
public class LogisticsServiceImpl implements LogisticsService {

    @Autowired
    private LogisticsMapper logisticsMapper;

    @Override
    public List<Logistics> getLogisticsByReceiver(String receiverName, String lStatus) {
        return logisticsMapper.getLogisticsByReceiver(receiverName, lStatus);
    }

    @Override
    public int updateLogistics(String lNumber, String lStatus) {
        return logisticsMapper.updateLogistics(lNumber, lStatus);
    }

    @Override
    public int addLogistics(Logistics logistics) {
        return logisticsMapper.addLogistics(logistics);
    }

    @Override
    public List<Logistics> getAllLogistics() {
        return logisticsMapper.getAllLogistics();
    }
}
