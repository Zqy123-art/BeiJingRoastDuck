package com.zqy.demo.dao;

import com.zqy.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Bean;

@Mapper
public interface UserDao {
    @Select("select * from user where username=#{username} limit 1")
    UserEntity getOneUser(String username);

    @Insert("insert into user(uid,password,username) value(#{uid},#{password},#{username})")
    int insert(UserEntity userEntity);


    @Select("select * from user where uid=#{uid} limit 1")
    UserEntity getOneUserByUid(@Param("uid") String uid);
}
