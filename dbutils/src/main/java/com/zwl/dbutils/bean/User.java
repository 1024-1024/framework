package com.zwl.dbutils.bean;

import com.zwl.dbutils.anotation.DbField;
import com.zwl.dbutils.anotation.DbTable;

/**
 * Created by weilongzhang on 17/1/10.
 */

@DbTable
public class User extends IBean{

    @DbField
    public String username;
    @DbField
    public int age;

    public boolean sex;

}
