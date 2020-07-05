package com.zqy.demo.service;

import com.zqy.demo.entity.UserTaskEntity;

import java.util.Date;
import java.util.List;

public interface TaskCalculateService {


    Boolean CopyFile(String oldpath,String newpath);

    List<String> FindTidWhoIsRunning(String uid,int status);

    String FindDateTimeByTid(String uid,String tid);
    int creatCalculateTask(String tid, String uid, Date startDate, String filename, int Status);
    int updateCalculateTaskStatus(String tid,String uid,int Status);

    List<UserTaskEntity> FindUserTaskByStatus(String uid,int status);


    String FindFileNameByTidAndUid(String uid,String tid);

    int DelByTid(String tid);


    int DelShowByTid(String tid);


}
