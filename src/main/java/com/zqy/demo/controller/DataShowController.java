package com.zqy.demo.controller;

import com.zqy.demo.resultcode.DataShowResult;
import com.zqy.demo.resultcode.ResultCode;
import com.zqy.demo.service.DataShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.ui.Model;
@Controller
public class DataShowController {

    @Autowired
    DataShowService dataShowService;

    @GetMapping("/data_show")
    public String dataShow(){

        return "datashow";
    }

    @PostMapping("/add_tid")
    @ResponseBody
    public ResultCode addTid(HttpServletRequest httpServletRequest, @RequestBody Map<String, String> map){

          httpServletRequest.getSession().setAttribute("tid",map.get("tid"));
          return new ResultCode("200","ok");
    }



    @PostMapping("/find_data")
    @ResponseBody
    public DataShowResult findData(HttpServletRequest httpServletRequest,Model model ){


       if (  httpServletRequest.getSession().getAttribute("tid")==null){
           DataShowResult dataShowResult = new DataShowResult("199","请在计算页面选择要查看的计算结果");
           return dataShowResult;
       }else {

           String  tid = (String) httpServletRequest.getSession().getAttribute("tid");
           String demand_1 = dataShowService.FindDataByTid(tid, "demand_1");
           String demand_2 = dataShowService.FindDataByTid(tid, "demand_2");
           String demand_3 = dataShowService.FindDataByTid(tid, "demand_3");
           String demand_4 = dataShowService.FindDataByTid(tid, "demand_4");
           String demand_5 = dataShowService.FindDataByTid(tid, "demand_5");
           String demand_6 = dataShowService.FindDataByTid(tid, "demand_6");
           String demand_7 = dataShowService.FindDataByTid(tid, "demand_7");

           String demand_8 = dataShowService.FindDataByTid(tid, "demand_8");

           String demand_9 = dataShowService.FindDataByTid(tid, "demand_9");

           String demand_10 = dataShowService.FindDataByTid(tid, "demand_10");
           String caldata = dataShowService.FindDataByTid(tid, "cal_date");







           DataShowResult dataShowResult = new DataShowResult("200","ok");

           dataShowResult.setDemand1(demand_1);
           dataShowResult.setDemand2(demand_2);
           dataShowResult.setDemand3(demand_3);
           dataShowResult.setDemand4(demand_4);
           dataShowResult.setDemand5(demand_5);
           dataShowResult.setDemand6(demand_6);
           dataShowResult.setDemand7(demand_7);
           dataShowResult.setDemand8(demand_8);
           dataShowResult.setDemand9(demand_9);
           dataShowResult.setDemand10(demand_10);
           dataShowResult.setCalDate(caldata);


           System.out.println(dataShowResult.getDemand1());

           System.out.println(dataShowResult.getDemand2());
           System.out.println(dataShowResult.getDemand3());
           System.out.println(dataShowResult.getDemand4());
           System.out.println(dataShowResult.getDemand5());
           System.out.println(dataShowResult.getDemand6());
           System.out.println(dataShowResult.getDemand7());
           System.out.println(dataShowResult.getDemand8());
           System.out.println(dataShowResult.getDemand9());
           System.out.println(dataShowResult.getDemand10());
           return dataShowResult;
       }


    }
}
