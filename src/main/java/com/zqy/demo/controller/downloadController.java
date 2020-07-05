package com.zqy.demo.controller;

import com.zqy.demo.entity.MovieInfoEntity;
import com.zqy.demo.resultcode.ResultCode;
import com.zqy.demo.service.DataCollectService;
import com.zqy.demo.service.DataDownLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class downloadController {



    @Autowired
    DataCollectService dataCollectService;

    @Autowired
    DataDownLoadService dataDownLoadService;
    /**
     *
     * 数据下载页面显示
     *
     * **/

    @GetMapping("/data_download")
    public String dataDownload(HttpServletRequest httpServletRequest, Model model){

        String uid = (String) httpServletRequest.getSession().getAttribute("uid");

        List<String> files = dataCollectService.findFileById(uid);

        model.addAttribute("files",files);

        return "/data_download";
    }





    /**
     *
     *  下载数据POST
     *
     * */
    //实现Spring Boot 的文件下载功能，映射网址为/download
    @GetMapping("/download")
    public String downloadFile(@RequestParam(value = "name", required = false) String name,
                               HttpServletResponse response,HttpServletRequest request) throws IOException {

        String uid = (String) request.getSession().getAttribute("uid");
        String filename = name;
        //获取文件名字
        String fileAllname = dataDownLoadService.FindFileAllName(uid, filename);
        // 获取指定目录下的第一个文件
        //如果文件名不为空，则进行下载
        if ( fileAllname != null) {
            //设置文件路径
            /**
             *  TODO
             * 修改linux路径
             * */
            String Path = "z:\\process_datas\\"+fileAllname;
            File file = new File(Path);

            // 如果文件名存在，则进行下载
            if (file.exists()) {
                // 配置文件下载
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                // 下载文件能正常显示中文
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileAllname, "UTF-8"));
                // 实现文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);

                        i = bis.read(buffer);
                    }
                    System.out.println("Download the song successfully!");
                }
                catch (Exception e) {
                    System.out.println("Download the song failed!");
                }
                finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }



    /**
     * 数据预览
     *
     * */
    @ResponseBody
    @PostMapping("/preview")
    public List<MovieInfoEntity> previewMovieInfo(@RequestBody Map<String,String> map,HttpServletRequest request){



        String filename = map.get("filename");
        String uid  = (String) request.getSession().getAttribute("uid");

        String allName = dataDownLoadService.FindFileAllName(uid, filename);

        List<MovieInfoEntity> list = dataDownLoadService.FindPreviewData(allName, 5);

        if (list==null){

            List<MovieInfoEntity> nullList = new ArrayList<>();
            return nullList;
        }else {
            return list;
        }



    }



    /**
     * 文件删除
     *
     * */
    @ResponseBody
    @PostMapping("/del")
    public ResultCode delectFile(@RequestBody Map<String,String> map,HttpServletRequest request){

        String filename = map.get("filename");
        String uid  = (String) request.getSession().getAttribute("uid");
        String allName = dataDownLoadService.FindFileAllName(uid, filename);

        String tid = dataCollectService.findTidByIdAndName(uid,filename);


        Boolean isdel = dataDownLoadService.delectFile(allName);



        if (isdel){
            //数据库中删除记录
            int i = dataCollectService.delUserTask(tid,uid,filename);
            System.out.println(i);

            return new ResultCode("200","删除成功");
        }else {
            return new ResultCode("203","删除失败");
        }





    }

}
