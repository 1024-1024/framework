package com.zwl.dbutils.bean;

import com.zwl.dbutils.anotation.DbField;
import com.zwl.dbutils.anotation.DbTable;

/**
 * Created by weilongzhang on 17/1/10.
 */

@DbTable
public class Teacher extends IBean{

    private String username;
    private String password;
    @DbField
    private String phone;

}
