package com.zqy.demo.controller;


import com.zqy.demo.dao.UserTaskDao;
import com.zqy.demo.entity.UserTaskEntity;
import com.zqy.demo.resultcode.ResultCode;
import com.zqy.demo.resultcode.TaskResult;
import com.zqy.demo.service.TaskCalculateService;
import com.zqy.demo.service.UserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserTaskController {

    @Autowired
    UserTaskService userTaskService;
    @Autowired
    TaskCalculateService taskCalculateServicel;
    /**
     * 任务管理页面
     *
     * */

    @GetMapping("/user_task")
    public String userTaskPage(HttpServletRequest request,Model model){


        String uid = (String) request.getSession().getAttribute("uid");


        List<UserTaskEntity> userTaskEntities = userTaskService.findAllfinished(uid);


        //查询完成的记录
        List<UserTaskEntity> userTaskEntities1 = taskCalculateServicel.FindUserTaskByStatus(uid, 2);


        model.addAttribute("CalculateTask", userTaskEntities1);
        //查询已经完成的任务
        model.addAttribute("userTaskEntities",userTaskEntities);
        return "my_task";
    }



    @PostMapping("/find_alltask_byId")
    @ResponseBody
    public List<UserTaskEntity> findallTask(HttpServletRequest request){
        String uid = (String) request.getSession().getAttribute("uid");



        List<UserTaskEntity> userTaskEntities = userTaskService.findAllfinished(uid);




        return  userTaskEntities;

    }




}
