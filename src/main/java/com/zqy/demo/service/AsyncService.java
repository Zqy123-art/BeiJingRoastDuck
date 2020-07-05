package com.zqy.demo.service;

import com.zqy.demo.resultcode.ResultCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public interface AsyncService {
    /**
     * 执行异步任务
     *  监听文件内容
     */
    void executeAsync(String uid,String tid,String filename,Map<String,String> taskRes);


    /**
     *
     * 监听文件行数
     * **/
    void executeAsyncPro(String uid,String file ,long allnum,Map<String,String> taskRes );

}
