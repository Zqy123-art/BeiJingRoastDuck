package com.zqy.demo.tool;

import com.zqy.demo.resultcode.ResultCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Stream;


/**
 * 实时监听文件内容
 * **/

public class FileListener {

    //设置标志变量来关闭线程
    private boolean isRun = true;

    //设置监听生成数据进度标志
    private boolean isproRun = true;

    private int linenum = 0;


    //监听进度文件路径
    //监听进度文件session
    public void run(String uid,String tid,String filepath,Map<String,String> taskRes) {

        while (isRun){
            File file = new File(filepath);
            //如果存在则监听，创建文件夹在flume里面创建
            if (file.exists()){

                //根据行号读取
                String res = null;
                res = readLine(filepath, linenum);
                if (res==""){

                }else if (!(res.equals("100"))){
                    //输出此行的内容
                    System.out.println(res);
                    //写入map中
                    if (res!=null){

                        taskRes.put(uid+tid,res);

                    }

                    linenum+=1;//行号加1
                }else if (res.equals("100")){

                    System.out.println(res);
                    taskRes.put(uid+tid,res);
                    isRun = false;
                    break;

                }

                try {
                    Thread.sleep(10);
                    //线程睡眠500
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }

    }


    public void produceRun(String uid,String filepath,long allnum,Map<String,String> taskRes ){
        try {

            long  status = allnum/10;
            while (isproRun){

                if(isLine(Files.lines(Paths.get(filepath)),filepath,allnum-1).equals("true")){

                    taskRes.put(uid,"100");
                    System.out.println(100);
                    //中止

                    isproRun = false;
                    break;
                }else if(isLine(Files.lines(Paths.get(filepath)),filepath,status*9).equals("true")){
                    taskRes.put(uid,"90");
                    System.out.println(90);
                }else if(isLine(Files.lines(Paths.get(filepath)),filepath,status*8).equals("true")){
                    taskRes.put(uid,"80");
                    System.out.println(80);
                }else if(isLine(Files.lines(Paths.get(filepath)),filepath,status*7).equals("true")){
                    taskRes.put(uid,"70");
                    System.out.println(70);
                }else if(isLine(Files.lines(Paths.get(filepath)),filepath,status*6).equals("true")){
                    taskRes.put(uid,"60");
                    System.out.println(60);
                }else if(isLine(Files.lines(Paths.get(filepath)),filepath,status*5).equals("true")){
                    taskRes.put(uid,"50");
                    System.out.println(50);
                }else if(isLine(Files.lines(Paths.get(filepath)),filepath,status*4).equals("true")){
                    taskRes.put(uid,"40");
                    System.out.println(40);
                }else if(isLine(Files.lines(Paths.get(filepath)),filepath,status*3).equals("true")){
                    taskRes.put(uid,"30");
                    System.out.println(30);
                }else if(isLine(Files.lines(Paths.get(filepath)),filepath,status*2).equals("true")){
                    taskRes.put(uid,"20");
                    System.out.println(20);
                }else if(isLine(Files.lines(Paths.get(filepath)),filepath,status).equals("true")){
                    taskRes.put(uid,"10");
                    System.out.println(10);
                }


                try {
                    Thread.sleep(10);
                    //线程睡眠500
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }



        } catch (IOException e) {
            e.printStackTrace();
        }



    }




















    /**
     * filename 文件名字
     * linenum 读取行号
     * **/
    public String readLine(String filename,int linenum){
        //方式1
//        String line = "";
//
//
//        try {
//            FileReader fileReader = new FileReader(filename);
//            BufferedReader bf = new BufferedReader(fileReader);
//
//
//            for (int i = 0;i<linenum;i++){
//                 bf.readLine();
//            }
//
//            line = bf.readLine();
//            bf.close();
//            fileReader.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return "-1";
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "-1";
//        }
//
//
//
//
//
//        return line;

        //方式2
//        try {
//            Stream<String> islines  = Files.lines(Paths.get(filename));
//            boolean b =islines.skip(linenum).findFirst().isPresent();
//
//
//            if (b){
//                //用完之后需要重新创建
//                Stream<String> lines = Files.lines(Paths.get(filename));
//                String  line = lines.skip(linenum).findFirst().get();
//                islines.close();
//                lines.close();
//                return line;
//            }else {
//                islines.close();
//                return "-2";
//                //返回-2代表没有数据
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "-1";
//        }

        //方式3
        String str = "";
        try {
            if(isLine(Files.lines(Paths.get(filename)),filename,linenum).equals("true")){
                Stream<String> lines = Files.lines(Paths.get(filename));
                String  line = lines.skip(linenum).findFirst().get();
                lines.close();
                str = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }






    /**
     * filename 文件名字
     * linenum 读取行号
     *
     * 判断有没有此行
     * **/
    private String isLine(Stream<String> islines ,String filename,long linenum){

        String s = String.valueOf(islines.skip(linenum).findFirst().isPresent());
        islines.close();
        return s;

    }




}
