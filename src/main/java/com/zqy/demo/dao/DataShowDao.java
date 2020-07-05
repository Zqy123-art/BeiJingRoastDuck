package com.zqy.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;



@Mapper
public interface DataShowDao {

    @Select("select ${column} from datashow where tid = '${tid}' ")
    String selectResByTid(@Param("tid")String id,@Param("column")String column);


}
