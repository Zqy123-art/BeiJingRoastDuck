package com.zqy.demo.dao;


import com.zqy.demo.entity.UserTaskEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserTaskDao {

    @Insert("insert into user_task(tid,uid,filename,startDate,endDate,status) value(#{tid},#{uid},#{filename},#{startDate},#{endDate},#{status})")
    int insertUserTask(UserTaskEntity userTaskEntity);

    //通过uid和filename获取
    @Select("select * from user_task where uid = '${id}' and filename = '${filename}'")
    List<UserTaskEntity> selectUserTaskByIdAndfilename(@Param("id")String id, @Param("filename")String filename);

    @Select("select * from user_task where uid = '${id}' and status = 0")
    List<UserTaskEntity> selectAllUserTaskById(@Param("id")String id);



    @Select("select filename from user_task where uid = '${uid}' and status = 0")
    List<String> selectFileNameById(@Param("uid")String uid);

    /**
     * 通过uid和filename查找tid
     * */
    @Select("select tid from user_task where  uid = '${id}' and filename = '${filename}' and status = 0")
    List<String> selectTidByIdAndFileName(@Param("id")String id, @Param("filename")String filename);



    @Delete("delete from user_task where tid = '${tid}' and uid = '${uid}' and filename = '${filename}'")
    int delByuidAndTid(@Param("tid")String tid,@Param("uid")String uid, @Param("filename")String filename);





}
