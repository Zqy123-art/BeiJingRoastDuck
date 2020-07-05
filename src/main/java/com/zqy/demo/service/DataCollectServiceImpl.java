package com.zqy.demo.service;

import com.zqy.demo.dao.UserTaskDao;
import com.zqy.demo.entity.UserTaskEntity;
import com.zqy.demo.tool.dataCollectTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class DataCollectServiceImpl implements DataCollectService {
    //执行命令
    dataCollectTool dc = new dataCollectTool();
    @Autowired
    UserTaskDao userTaskDao;


    /**
     *
     * 生成数据功能
     * **/
    @Override
    public int produceData(String tid,String uid,long num, String startDate, String endDate, String filename) {
        UserTaskEntity userTaskEntity = new UserTaskEntity();
        //产生taskid

        userTaskEntity.setUid(uid);
        userTaskEntity.setTid(tid);
        userTaskEntity.setStartDate(new Date());
        userTaskEntity.setEndDate(null);
        userTaskEntity.setFilename(filename);
        userTaskEntity.setStatus(0);



        /**
         * TODO
         * 需要替换linux命令
         *
         * **/
        int result = dc.shell("python z:\\douban_source\\douban_python\\information.py " + tid + " " + filename + " " + num + " " + startDate + " " + endDate);

        if (!dc.isHasException()&&result==0) {
            //插入记录
            userTaskDao.insertUserTask(userTaskEntity);
            return result;
        }else {
            //出错
            
            return 1;
        }




    }


    /**
     * 判断名字是否相同
     * */
    @Override
    public boolean fileIsIdentical(String uid, String filename) {
        List<UserTaskEntity> uts = userTaskDao.selectUserTaskByIdAndfilename(uid, filename);
        if (uts.size()==0){
            return false;
        }else {
            return true;
        }

    }

    /**
     * 根据uid查找所有file
     *
     * */
    @Override
    public List<String> findFileById(String uid){

        return userTaskDao.selectFileNameById(uid);

    }

    /**
     *
     * 根据uid和文件名查找tid
     * */
    public String findTidByIdAndName(String uid,String filename){
       return userTaskDao.selectTidByIdAndFileName(uid,filename).get(0);
    }



    @Override
    public int delUserTask(String tid,String uid, String filename) {
        return userTaskDao.delByuidAndTid(tid,uid,filename);
    }

}
