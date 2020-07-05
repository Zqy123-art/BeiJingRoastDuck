package com.zqy.demo.dao;

import com.zqy.demo.entity.UserTaskEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskCalculateDao {

    @Select("select tid from user_task where uid = '${id}' and status = '${status}'")
    List<String> selectTidWhoIsRunning(@Param("id")String id,@Param("status")int status);


    @Select("select startDate from user_task where uid = '${id}' and tid = '${tid}'")
    List<String> selectdateByTid(@Param("id")String id,@Param("tid")String tid);



    @Update("update user_task set status = ${status} where uid = '${id}' and tid = '${tid}'")
    int updateStatusByTid(@Param("status")int status,@Param("tid")String tid,@Param("id")String id);


    @Select("select * from user_task where uid = '${id}' and status = '${status}'")
    List<UserTaskEntity> selectAllByStatus(@Param("id")String id, @Param("status")int status);


    @Select("select filename from user_task where uid = '${id}' and tid = '${tid}'")
    String selectFileNameByTidAndUid(@Param("id")String id, @Param("tid")String tid);


    @Delete("delete from user_task where tid = '${tid}'")
    int delByTid(@Param("tid")String tid);

    @Delete("delete from datashow where tid = '${tid}'")
    int delShowByTid(@Param("tid")String tid);





}
