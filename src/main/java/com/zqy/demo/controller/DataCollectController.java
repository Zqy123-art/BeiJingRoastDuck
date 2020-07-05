package com.zqy.demo.controller;


import com.zqy.demo.entity.UserEntity;
import com.zqy.demo.resultcode.ResultCode;
import com.zqy.demo.resultcode.TaskResult;
import com.zqy.demo.service.AsyncService;
import com.zqy.demo.service.DataCollectService;
import com.zqy.demo.tool.dataCollectTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * 数据生成和下载
 * **/

@Controller
public class DataCollectController {

    @Autowired
    DataCollectService dataCollectService;
    @Autowired
    AsyncService asyncService;



    //用于前端获取进度值
    Map<String,String> proMap = new HashMap<>();





    /**
     *生成数据页面显示
     **/
    @GetMapping("/data_produce")
    public String produce(){

        return "data_produce";
    }





    /**
     *  数据生成页面POST
     *
     * **/
    @PostMapping("/data_pro")
    @ResponseBody
    public  ResultCode dataproduce(@RequestBody Map<String,String> map, HttpServletRequest httpServletRequest){


        long dataNum = Integer.parseInt(map.get("dataNum"));
        String startDate = map.get("startDate");
        String endDate = map.get("endDate");
        String fileName = map.get("fileName");

        /**
         * 模拟从session中获取uid
         * TODO
         **/
        String uid = (String) httpServletRequest.getSession().getAttribute("uid");
        String tid = UUID.randomUUID().toString().replaceAll("-","");

        if (proMap.containsKey(uid+"ProisRunning")&&proMap.get(uid+"ProisRunning").equals("true")){
            return new ResultCode("205","你正在执行一个采集进程，请刷新一下页面看看进度吧");
        }



        proMap.put(uid+"ProisRunning","true");
        //判断是否执行



        //清空
        //taskRes.setMessage("0");


        proMap.put(uid,"0");
        proMap.put(uid+"tid",tid);
        proMap.put(uid+"startDate",startDate);

        //判断是否有同名文件
        boolean isIdentical = dataCollectService.fileIsIdentical(uid, fileName);
        if (isIdentical){
            //ProisRunning = "false";
            proMap.put(uid+"ProisRunning","false");
            return new ResultCode("201","发现此文件已经被创建,请换一下文件名吧");
        }

        /**
         * TODO
         * 需要替换linux命令
         *
         * **/

        //根据tid创建进度文件夹
        dataCollectTool dc = new dataCollectTool();

        String processFilenamePath = "z:\\process_datas\\"+ tid+"@"+fileName+".json";


        /**
         * TODO
         * 需要替换linux命令
         *
         * 存放数据文件的路径
         * **/

        int mkdirres = dc.shell("cmd /c type nul>z:\\process_datas\\"+tid+"@"+fileName+".json");



        //判断是否创建文件成功
        if (mkdirres!=0){
            ResultCode resultCode =  new ResultCode("500","创建存储文件夹失败");
            System.out.println("创建存储文件夹失败");
            return resultCode;
        }



        //开启进度文件夹监听
        asyncService.executeAsyncPro(uid,processFilenamePath,dataNum,proMap);

        //修改标志
        //ProisRunning = "true";TODO



        int res = dataCollectService.produceData(tid,uid, dataNum, startDate, endDate, fileName);

        if (res==0){
            ResultCode resultCode = new ResultCode("200","success");
            //修改标志
            //ProisRunning = "false";
            proMap.remove(uid+"ProisRunning");
            proMap.remove(uid);
            proMap.remove(uid+"tid");
            proMap.remove(uid+"startDate");

            return resultCode;
        }else {

            ResultCode resultCode =  new ResultCode("500","error");
            // ProisRunning = "false";
            proMap.remove(uid+"ProisRunning");
            proMap.remove(uid);
            proMap.remove(uid+"tid");
            proMap.remove(uid+"startDate");
            return resultCode;
        }


    }






    /**
     * 返回数据生成进度
     * **/
    @PostMapping("/task_pro")
    @ResponseBody
    public  ResultCode taskProccess( HttpServletRequest httpServletRequest){
        //获取uid来进行判断
        String uid = (String) httpServletRequest.getSession().getAttribute("uid");


        return new ResultCode("200",proMap.get(uid));
    }




    /**
     *
     * 判断数据生成是否正在进行
     * **/
    @PostMapping("/is_pro_running")
    @ResponseBody
    public ResultCode proIsRunning( HttpServletRequest httpServletRequest){
        String uid = (String) httpServletRequest.getSession().getAttribute("uid");

        if (proMap.containsKey(uid+"ProisRunning")&&proMap.get(uid+"ProisRunning").equals("true")){
            return new ResultCode("200","true");

        }else {
            return new ResultCode("200","false");

        }

    }



    /**
     * task页面传请求查看任务是否正在进行
     *
     * */

    @PostMapping("/check_task")
    @ResponseBody
    public TaskResult checkTask( HttpServletRequest httpServletRequest){
        String uid = (String) httpServletRequest.getSession().getAttribute("uid");





        if (proMap.containsKey(uid+"ProisRunning")&&proMap.get(uid+"ProisRunning").equals("true")){

            TaskResult taskResult = new TaskResult();
            taskResult.setStartDate(proMap.get(uid+"startDate"));
            taskResult.setTid(proMap.get(uid+"tid"));
            taskResult.setTaskProccess(proMap.get(uid));
            taskResult.setUid(uid);
            taskResult.setTaskIsRunning("true");

            return taskResult;
        }else {

            TaskResult taskResult = new TaskResult();
            taskResult.setStartDate(null);
            taskResult.setTid(null);
            taskResult.setUid(uid);
            taskResult.setTaskProccess(null);
            taskResult.setTaskIsRunning("false");

            return taskResult;
        }
    }

}
