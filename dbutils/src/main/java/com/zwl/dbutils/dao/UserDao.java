package com.zwl.dbutils.dao;

public class UserDao extends BaseDao {
    @Override
    public String createTable() {
        return "create table if not exists tb_user(name varchar(20),password varchar(10),age int )";
    }
}
