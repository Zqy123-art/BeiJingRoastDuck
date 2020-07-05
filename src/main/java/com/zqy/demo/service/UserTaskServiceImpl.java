package com.zqy.demo.service;

import com.zqy.demo.dao.UserTaskDao;
import com.zqy.demo.entity.UserTaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTaskServiceImpl implements UserTaskService {


    @Autowired
    UserTaskDao userTaskDao;
    @Override
    public List<UserTaskEntity> findAllfinished(String uid) {



        return userTaskDao.selectAllUserTaskById(uid);


    }
}
