package com.zwl.dbutils;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.zwl.dbutils.dao.BeanDao;

import java.io.File;
import java.util.HashMap;

/**
 * Created by weilongzhang on 17/1/10.
 */

public class DbUtilsFactory {

    private volatile static DbUtilsFactory dbUtilsFactory;
    private static SQLiteDatabase sqLiteDatabase;
    private static File dbFile;
    private HashMap<Class, BeanDao> daoHashMap;

    private DbUtilsFactory(){
        dbFile = new File(Environment.getDataDirectory().getAbsolutePath());
        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
        daoHashMap = new HashMap<Class, BeanDao>();
    }

    public static DbUtilsFactory getInstance() {
        if (dbUtilsFactory == null) {
            synchronized (DbUtilsFactory.class) {
                if (dbUtilsFactory == null) {
                    dbUtilsFactory = new DbUtilsFactory();
                }
            }
        }
        return dbUtilsFactory;
    }


    public SQLiteDatabase getSqlLiteDatabase() {
        if (sqLiteDatabase == null) {
            dbFile = new File(Environment.getDataDirectory().getAbsolutePath());
            sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
        }
        return sqLiteDatabase;
    }


    public <T extends BeanDao> T getDao(Class clazz, Class cls) {
        if (daoHashMap.containsKey(clazz)) {
            return (T)daoHashMap.get(clazz);
        }
        try {
            T beanDao = (T) clazz.newInstance();
            daoHashMap.put(clazz, beanDao);
            beanDao.init(clazz, cls);
            return beanDao;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }




}
