package com.zqy.demo.service;

import com.zqy.demo.resultcode.ResultCode;
import com.zqy.demo.tool.FileListener;
import com.zqy.demo.tool.dataCollectTool;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Queue;

/**
 * 执行异步任务,线程
 *
 */


@Service
public class AsyncServiceImpl implements  AsyncService{
    private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

    /**
     *
     * 监听文件内容
     *
     * **/

    @Async("asyncServiceExecutor")
    @Override
    public void executeAsync(String uid,String tid,String filename,Map<String,String> taskRes) {
        logger.info("start executeAsync");
        try{

            FileListener fileListener = new FileListener();
            fileListener.run(uid,tid,filename, taskRes);


            //删除文件夹
            dataCollectTool dc = new dataCollectTool();





        }catch(Exception e){
            e.printStackTrace();

        }

        logger.info("end executeAsync");


        /**
         * TODO
         * 删除文件
         * **/
        dataCollectTool dc = new dataCollectTool();


            int delres = dc.shell("cmd /c del /F/S/Q " + filename);

            if (delres == 0 && (!dc.isHasException())) {
                System.out.println("删除文件" + filename + "成功");
            } else {
                System.out.println("删除文件" + filename + "失败");
            }






    }


    /**
     *
     * 监听文件行数
     *
     * **/

    @Override
    @Async("asyncServiceExecutor")
    public void executeAsyncPro(String uid,String file,long allnum,Map<String,String> taskRes ) {
        logger.info("start executeAsyncPro");
        try{

            FileListener fileListener = new FileListener();
            fileListener.produceRun(uid,file,allnum,taskRes );
        }catch(Exception e){
            e.printStackTrace();
        }
        logger.info("end executeAsyncPro");

    }


}
