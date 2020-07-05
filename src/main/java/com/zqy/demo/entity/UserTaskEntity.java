package com.zqy.demo.entity;

import lombok.Data;


import java.util.Date;

@Data
public class UserTaskEntity {
    String uid;
    String tid;
    String filename;
    Date startDate;
    Date endDate;
    int status;
}
