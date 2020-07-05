package com.zqy.demo.resultcode;

public class TaskResult {
    String taskIsRunning;
    String uid;
    String tid;
    String startDate;
    String taskProccess;
    String code;
    String message;

    public TaskResult() {
    }

    public TaskResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getTaskProccess() {
        return taskProccess;
    }

    public void setTaskProccess(String taskProccess) {
        this.taskProccess = taskProccess;
    }

    public String getTaskIsRunning() {
        return taskIsRunning;
    }

    public void setTaskIsRunning(String taskIsRunning) {
        this.taskIsRunning = taskIsRunning;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
