package com.zqy.demo.controller;

import com.zqy.demo.entity.UserTaskEntity;
import com.zqy.demo.resultcode.ResultCode;
import com.zqy.demo.resultcode.TaskResult;
import com.zqy.demo.service.AsyncService;
import com.zqy.demo.service.DataCollectService;
import com.zqy.demo.service.DataDownLoadService;
import com.zqy.demo.service.TaskCalculateService;
import com.zqy.demo.tool.dataCollectTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class CalculateController {

    @Autowired
    DataCollectService dataCollectService;

    @Autowired
    DataDownLoadService dataDownLoadService;

    @Autowired
    TaskCalculateService taskCalculateServicel;

    @Autowired
    AsyncService asyncService;


    //用于前端获取进度值
    Map<String, String> proMap = new HashMap<>();


    @GetMapping("/calculate")
    public String Sparkcalculate(HttpServletRequest httpServletRequest, Model model) {


        String uid = (String) httpServletRequest.getSession().getAttribute("uid");

        List<String> files = dataCollectService.findFileById(uid);


        //查询完成的记录
        List<UserTaskEntity> userTaskEntities = taskCalculateServicel.FindUserTaskByStatus(uid, 2);


        model.addAttribute("CalculateTask", userTaskEntities);


        model.addAttribute("files", files);
        return "calculate";
    }


    @PostMapping("/start_calculate")
    @ResponseBody
    public TaskResult task_start(HttpServletRequest httpServletRequest, @RequestBody Map<String, String> map) {



        String uid = (String) httpServletRequest.getSession().getAttribute("uid");

        String filename = map.get("filename");

        List<String> tids = taskCalculateServicel.FindTidWhoIsRunning(uid, 1);


        if (proMap.containsKey(uid + filename + ":isCal")) {
            return new TaskResult("210", "文件正在进行计算");
        }
        if (tids.size()>3){
            return new TaskResult("210", "正在计算的文件不能超过3个");
        }



        //创建计算tid
        String tid = UUID.randomUUID().toString().replaceAll("-", "");




        String allName = dataDownLoadService.FindFileAllName(uid, filename);
        String newAllName = tid+"@"+filename+".json";
        String oldpath = "z:process_datas\\" + allName;
        //拷贝并且重命名
        String newpath = "z:movie_datas\\datas\\"+newAllName;

        //拷贝
        Boolean isCopy = taskCalculateServicel.CopyFile(oldpath, newpath);



        proMap.put(uid + tid + ":Allfilename", newAllName);
        proMap.put(uid + tid, "0");

        //正在运行设置为true
        proMap.put(uid + filename+ ":isCal", "true");


        //创建监听文件夹
        String listenfile = "z:\\calculate\\" + newAllName;

//
//        dataCollectTool dc = new dataCollectTool();
//        int mkdirres = dc.shell("cmd /c type nul>" + listenfile);
//        //判断是否创建文件成功
//        if (mkdirres != 0) {
//
//            System.out.println("创建进度文件失败");
//            return new TaskResult("500", "创建进度文件失败");
//        }


        //监听文件夹

        asyncService.executeAsync(uid, tid, listenfile, proMap);


        Date startDate = new Date();



        if (isCopy) {
            TaskResult t = new TaskResult();
            t.setTaskIsRunning("true");
            t.setTaskProccess(proMap.get(uid + tid));
            t.setUid(uid);
            t.setTid(tid);
            t.setCode("200");
            t.setMessage("开始计算");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sdf.format(startDate);
            t.setStartDate(format);
            //插入数据
            int i = taskCalculateServicel.creatCalculateTask(tid, uid, startDate, filename, 1);

            return t;
        } else {
            return new TaskResult("300", "拷贝文件失败");
        }


        //Key为uid+tid的是进度
        //Key为uid+tid+":isCal"的是否正在进行

    }


    /**
     * 返回数据生成进度
     **/

    @PostMapping("/cal_task_pro")
    @ResponseBody
    public List<TaskResult> taskProccess(HttpServletRequest httpServletRequest) {
        //获取uid来进行判断



        String uid = (String) httpServletRequest.getSession().getAttribute("uid");
        List<TaskResult> list = new ArrayList<>();


        //0:数据生成,1:数据计算,2:数据计算完成
        List<String> tids = taskCalculateServicel.FindTidWhoIsRunning(uid, 1);


        for (String tid : tids) {
            TaskResult t = new TaskResult();
            t.setTid(tid);
            t.setUid(uid);


            if (proMap.containsKey(uid + tid) && proMap.get(uid + tid).equals("100")) {
                t.setTaskProccess(proMap.get(uid + tid));
                t.setTaskIsRunning("false");
                taskCalculateServicel.updateCalculateTaskStatus(tid, uid, 2);//计算完成

                String filename = taskCalculateServicel.FindFileNameByTidAndUid(uid, tid);
                //删除map
                proMap.remove(uid + tid);
                proMap.remove(uid + filename + ":isCal");
                String allfilename = proMap.get(uid + tid + ":Allfilename");

                File file = new File("Z:\\movie_datas\\datas\\" + allfilename);


                //删除文件
                dataCollectTool dc = new dataCollectTool();
                /**
                 *  TODO
                 * ".COMPLETED"
                 * **/
                int res = dc.shell("cmd /c del /F/S/Q Z:\\movie_datas\\datas\\" + allfilename+".COMPLETED");

                if (res == 0 && (!dc.isHasException())) {
                    System.out.println("删除文件Z:\\movie_datas\\datas\\" + allfilename + "成功");
                } else {
                    System.out.println("删除文件Z:\\movie_datas\\datas\\" + allfilename + "失败");

                }
                proMap.remove(uid + tid + ":Allfilename");


            } else {

                t.setTaskProccess(proMap.get(uid + tid));
                t.setTaskIsRunning("true");

            }

            t.setStartDate(taskCalculateServicel.FindDateTimeByTid(uid, tid));

            list.add(t);


        }

        //返回进度，可以根据list的元素数量来判断

        return list;
    }


    /**
     * 查询所有完成的计算任务
     **/
    @PostMapping("/find_finish_caltask")
    @ResponseBody
    public List<UserTaskEntity> findAllFinishCalTask(HttpServletRequest httpServletRequest) {

        String uid = (String) httpServletRequest.getSession().getAttribute("uid");
        //查询完成的记录
        List<UserTaskEntity> userTaskEntities = taskCalculateServicel.FindUserTaskByStatus(uid, 2);
        return userTaskEntities;


    }

    /**
     * 删除已完成的
     *
     * **/

    @PostMapping("/del_calTask")
    @ResponseBody
    @Transactional
    public ResultCode delCalTask(HttpServletRequest httpServletRequest,@RequestBody Map<String, String> map) {

        String tid = map.get("tid");

        //查询完成的记录

        int i = taskCalculateServicel.DelByTid(tid);
        taskCalculateServicel.DelShowByTid(tid);
        if (i==0){
            return new ResultCode("301","删除失败");

        }else {

            return new ResultCode("200","删除成功");

        }

    }



}
