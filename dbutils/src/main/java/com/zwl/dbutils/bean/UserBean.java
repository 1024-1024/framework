package com.zwl.dbutils.bean;


import com.zwl.dbutils.annotation.DbTable;

@DbTable("tb_user")
public class UserBean {
    //tb_name

    public String name;
    public String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "name : "+name+" password  "+password;
    }
}
