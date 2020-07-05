package com.zqy.demo.service;
import com.zqy.demo.dao.UserDao;
import com.zqy.demo.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserDao userDao;

    public UUID uuid=UUID.randomUUID();

    public String register(String username,String password){

        if (userDao.getOneUser(username)==null){
            UserEntity userEntity=new UserEntity();
            userEntity.setUid(String.valueOf(uuid));
            userEntity.setUsername(username);
            userEntity.setPassword(password);
            userDao.insert(userEntity);
            return "注册成功";
        }
        else {
            return "用户名已经被使用";
        }
    }

    public UserEntity login(String username,String password){
        return userDao.getOneUser(username);

    }

    @Override
    public UserEntity findUserByUid(String uid) {
        return userDao.getOneUserByUid(uid);
    }

}
