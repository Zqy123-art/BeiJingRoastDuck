package com.zqy.demo.controller;

import com.zqy.demo.entity.UserEntity;
import com.zqy.demo.resultcode.ResultCode;
import com.zqy.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(HttpServletResponse response){

        return "data_produce";
    }
    @RequestMapping(value = "/regist")
    public String registerGet(){
        return "regist";
    }

    @PostMapping(value = "/toregist")
    @ResponseBody
    public ResultCode registerPost(Model model, @RequestBody Map<String,String> map,
                                   HttpServletResponse response){
        String username = map.get("username");
        String password = map.get("password");
        String result=userService.register(username,password);
        if (result.equals("注册成功")){
            return new ResultCode("200",result);
        }else {
            return new ResultCode("100",result);
        }

    }
    @RequestMapping(value = "/login")
    public String loginGet(){
        return "login";
    }

    @PostMapping(value = "/login_post")
    @ResponseBody
    public ResultCode loginPost(Model model,
                            @RequestBody Map<String,String> map,
                            HttpServletRequest request){
     String username = map.get("username");
     String password = map.get("password");
     UserEntity user=userService.login(username,password);

     if (user==null){

         return new ResultCode("101","没有此用户");
     }

     if (password.equals(user.getPassword())){
//         session.setAttribute("user",userEntity);
         request.getSession().setAttribute("uid",user.getUid());
         request.getSession().setAttribute("password",user.getPassword());
         return new  ResultCode("200","欢迎光临");
     }else {
         model.addAttribute("message","用户名或密码错误");
         return new  ResultCode("102","用户名或密码错误");

     }
    }
    @RequestMapping(value = "/loginOut",method = RequestMethod.GET)
    public String loginOut(HttpServletRequest request){
        request.getSession().removeAttribute("uid");
        request.getSession().removeAttribute("password");
        return "login";
    }

}
