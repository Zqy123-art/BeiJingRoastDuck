package com.zqy.demo.service;

import com.zqy.demo.dao.DataShowDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataShowServiceImpl implements DataShowService {


    @Autowired
    DataShowDao dataShowDao;

    @Override
    public String FindDataByTid(String tid, String column) {


        return dataShowDao.selectResByTid(tid,column);

    }
}
