package com.zqy.demo.service;

import com.zqy.demo.entity.UserTaskEntity;

import java.util.List;

public interface UserTaskService {


    List<UserTaskEntity> findAllfinished(String uid);
}
