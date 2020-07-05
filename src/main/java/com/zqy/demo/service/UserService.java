package com.zqy.demo.service;


import com.zqy.demo.entity.UserEntity;

public interface UserService {
    String register(String username,String password);
    UserEntity login(String username,String password);


    UserEntity findUserByUid(String uid);
}



