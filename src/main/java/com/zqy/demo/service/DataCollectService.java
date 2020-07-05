package com.zqy.demo.service;


import java.util.List;

public interface DataCollectService {

    /**
     * 数据生成
     * **/
    int produceData(String tid,String uid,long num,String startDate,String endDate,String filename);

    /**
     * 数据查询
     * 根据uid判断是否有相同的名字
     * */

    boolean fileIsIdentical(String uid,String filename);

    List<String> findFileById(String uid);


    int delUserTask(String tid,String uid,String filename);

    String findTidByIdAndName(String uid,String filename);
}
