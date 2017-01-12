package com.zwl.dbutils;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.zwl.dbutils.dao.BaseDao;

import java.io.File;


public class DaoManagerFactory {
    private String sqliteDataPath;
    private SQLiteDatabase sqLiteDatabase;

    public static DaoManagerFactory instanse = new DaoManagerFactory(new File(Environment
            .getExternalStorageDirectory(), "logic.db"));

    public static DaoManagerFactory getInstance() {
        return instanse;
    }

    private DaoManagerFactory(File file) {
        this.sqliteDataPath = file.getAbsolutePath();
        openDatabase();
    }

    public synchronized <T extends BaseDao<M>, M> T getDataHelper(Class<T> clazz, Class<M>
            entityClass) {
        BaseDao baseDao = null;
        try {
            baseDao = clazz.newInstance();
            baseDao.init(entityClass, sqLiteDatabase);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return (T) baseDao;
    }

    private void openDatabase() {
        this.sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(sqliteDataPath, null);
    }
}
