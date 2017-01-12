package com.zwl.dbutils;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.zwl.dbutils.dao.BaseDao;
import com.zwl.dbutils.exception.DbTableMissingException;

import java.io.File;
import java.util.HashMap;


public class DaoManagerFactory {

    private String sqlLiteDataPath;
    private SQLiteDatabase sqLiteDatabase;

    private HashMap<Class<? extends BaseDao>, BaseDao> daoHashMap;

    public static DaoManagerFactory instance = new DaoManagerFactory(new File(Environment
            .getExternalStorageDirectory(), "zwl.db"));

    public static DaoManagerFactory getInstance() {
        return instance;
    }

    private DaoManagerFactory(File file) {
        this.sqlLiteDataPath = file.getAbsolutePath();
        daoHashMap = new HashMap<Class<? extends BaseDao>, BaseDao>();
        openDatabase();
    }

    public synchronized <T extends BaseDao<M>, M> T getDataHelper(Class<T> clazz, Class<M>
            entityClass) {
        if (daoHashMap.containsKey(clazz)) {
            return (T) daoHashMap.get(clazz);
        }
        BaseDao baseDao = null;
        try {
            baseDao = clazz.newInstance();
            baseDao.init(entityClass, sqLiteDatabase);
            daoHashMap.put(clazz, baseDao);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (DbTableMissingException e) {
            e.printStackTrace();
        }
        return (T) baseDao;
    }

    private void openDatabase() {
        this.sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(sqlLiteDataPath, null);
    }
}
