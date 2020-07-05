package com.zqy.demo.service;

import com.zqy.demo.dao.TaskCalculateDao;
import com.zqy.demo.dao.UserTaskDao;
import com.zqy.demo.entity.UserTaskEntity;
import com.zqy.demo.tool.dataCollectTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskCalculateServiceImpl implements TaskCalculateService {

    @Autowired
    TaskCalculateDao taskCalculateDao;


    @Autowired
    UserTaskDao userTaskDao;


    /**
     *
     * 生成数据功能
     * **/
    @Override
    public int creatCalculateTask(String tid,String uid,Date startDate,String filename,int Status) {
        UserTaskEntity userTaskEntity = new UserTaskEntity();
        //产生taskid

        userTaskEntity.setUid(uid);
        userTaskEntity.setTid(tid);
        userTaskEntity.setStartDate(startDate);
        userTaskEntity.setEndDate(null);
        userTaskEntity.setFilename(filename);
        userTaskEntity.setStatus(Status);
        int i = userTaskDao.insertUserTask(userTaskEntity);
        return i;
    }

    @Override
    public int updateCalculateTaskStatus(String tid, String uid, int Status) {

        return  taskCalculateDao.updateStatusByTid(Status, tid, uid);

    }

    @Override
    public List<UserTaskEntity> FindUserTaskByStatus(String uid, int status) {
        return taskCalculateDao.selectAllByStatus(uid,status);
    }

    @Override
    public String FindFileNameByTidAndUid(String uid, String tid) {
        return taskCalculateDao.selectFileNameByTidAndUid(uid,tid);
    }

    @Override
    public int DelByTid(String tid) {
        return taskCalculateDao.delByTid(tid);
    }

    @Override
    public int DelShowByTid(String tid) {
        return taskCalculateDao.delShowByTid(tid);
    }



    @Override
    public Boolean CopyFile(String oldpath, String newpath) {

        dataCollectTool dc = new dataCollectTool();
        String cmd = "cmd /c copy "+oldpath+" "+newpath;
        int res = dc.shell(cmd);

        if (res==0&&(!dc.isHasException())){
            return true;
        }else {
            return false;
        }


    }

    @Override
    public List<String> FindTidWhoIsRunning(String uid, int status) {
        return  taskCalculateDao.selectTidWhoIsRunning(uid,status);
    }

    @Override
    public String FindDateTimeByTid(String uid, String tid) {
        return taskCalculateDao.selectdateByTid(uid,tid).get(0);
    }





}
