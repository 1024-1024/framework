package com.zwl.dbutils.dao;

import android.content.ContentValues;

import com.zwl.dbutils.bean.User;

/**
 * Created by weilongzhang on 17/1/10.
 */

public class UserDao extends BeanDao<User> {

    @Override
    protected void otherType(ContentValues contentValues) {
    }
}
