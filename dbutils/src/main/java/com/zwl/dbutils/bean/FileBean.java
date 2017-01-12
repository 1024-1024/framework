package com.zwl.dbutils.bean;

import com.zwl.dbutils.annotation.DbTable;

@DbTable("tb_file")
public class FileBean {

    public String  time;
    public  String path;
    public  String decription;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
}
