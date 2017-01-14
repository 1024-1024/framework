package com.zwl;

import android.app.Application;
import android.content.Context;

import com.zwl.dbutils2.DataBaseManager;
import com.zwl.dbutils2.DemoDataBase;

/**
 * Created by weilongzhang on 17/1/12.
 */

public class App extends Application {

    public static Context appContext;
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;

        //注册数据库服务
        DataBaseManager.getDataBaseManager().registDataBase(new DemoDataBase(this));

    }
}
