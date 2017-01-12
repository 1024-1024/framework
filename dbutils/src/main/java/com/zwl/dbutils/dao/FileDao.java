package com.zwl.dbutils.dao;

public class FileDao extends BaseDao {
    @Override
    public String createTable() {
        return "create table if not exists tb_file(time varchar(20),path varchar(10),decription " +
                "varchar(20))";

    }
}
