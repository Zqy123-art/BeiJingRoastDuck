package com.zqy.demo.service;

import com.zqy.demo.dao.UserTaskDao;
import com.zqy.demo.entity.MovieInfoEntity;
import com.zqy.demo.tool.dataCollectTool;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;
@Service
public class DataDownLoadServiceImpl implements DataDownLoadService {


    @Autowired
    UserTaskDao userTaskDao;
    @Override
    public String FindFileAllName(String uid, String fileName) {

        List<String> list = userTaskDao.selectTidByIdAndFileName(uid, fileName);



        if (list.size()!=0){
        //获取到tid
            String tid = list.get(0);
            return tid+"@"+fileName+".json";
        }else {
            //没有文件
            return null;
        }


    }


    /**
     * linux path TODO
     *
     * */
    @Override
    public List<MovieInfoEntity> FindPreviewData(String allfilename,int readline) {

        int readnum = 0;

        int fileExist = 1;

        ArrayList<MovieInfoEntity> list = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader("z:\\process_datas\\"+allfilename);

            BufferedReader bf = new BufferedReader(fileReader);
            String str;
            while ((str=bf.readLine())!=null&&readnum<readline){
                //转成map
                Map<String,String> jsonMap = (Map) JSON.parse(str);

                MovieInfoEntity movieInfoEntity = new MovieInfoEntity();
                movieInfoEntity.setMovieId(String.valueOf(jsonMap.get("movie_id")));
                movieInfoEntity.setMovieLanguage(String.valueOf(jsonMap.get("movie_language")));
                movieInfoEntity.setMovieLong(String.valueOf(jsonMap.get("movie_long")));
                movieInfoEntity.setMovieType(String.valueOf(jsonMap.get("movie_type")));
                movieInfoEntity.setMovieTime(String.valueOf(jsonMap.get("movie_time")));
                movieInfoEntity.setMovieScore(String.valueOf(jsonMap.get("movie_score")));

                list.add(movieInfoEntity);
                readnum+=1;

            }
            fileReader.close();
            bf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileExist = 0;

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fileExist==0){
            //文件不存在
            return null;
        }else {
            return list;
        }


    }

    @Override
    public Boolean delectFile(String allfilename) {

        dataCollectTool dc = new dataCollectTool();
        int res = dc.shell("cmd /c del /F/S/Q z:\\process_datas\\" + allfilename);




        if (res==0&&(!dc.isHasException())){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public String FindTidByFileNameAndUid(String uid, String fileName) {
        List<String> list = userTaskDao.selectTidByIdAndFileName(uid, fileName);



        if (list.size()!=0){
            //获取到tid
            String tid = list.get(0);
            return tid;
        }else {
            //没有文件
            return null;
        }
    }


}
