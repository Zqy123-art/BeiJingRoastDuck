package com.zqy.demo.service;

import com.zqy.demo.entity.MovieInfoEntity;

import java.util.List;

public interface DataDownLoadService {

    String FindFileAllName(String uid,String fileName);

    List<MovieInfoEntity> FindPreviewData(String allfilename,int readline);

    Boolean delectFile(String allfilename);

    String FindTidByFileNameAndUid(String uid,String fileName);

}
