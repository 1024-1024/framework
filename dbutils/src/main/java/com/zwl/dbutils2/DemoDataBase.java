package com.zwl.dbutils2;

import android.content.Context;

import com.zwl.App;
import com.zwl.dbutils2.table.DataCacheTable;

/**
 * Created by weilongzhang on 17/1/14.
 */

public class DemoDataBase extends DataBaseHelper {

    public static final String DATABASE_NAME = "test.db";
    public static final int DATABASE_VERSION = 1;

    public DemoDataBase(Context context) {
        this(context, DATABASE_NAME, DATABASE_VERSION);
    }


    public DemoDataBase(Context context, String name, int version) {
        super(App.appContext, DATABASE_NAME, DATABASE_VERSION);
    }

    @Override
    public void initTables(DataBaseHelper db) {
        addTable(DataCacheTable.class, new DataCacheTable(db));
    }
}
