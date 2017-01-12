package com.zwl.dbutils.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.zwl.dbutils.DbUtilsFactory;
import com.zwl.dbutils.anotation.DbField;
import com.zwl.dbutils.anotation.DbTable;
import com.zwl.dbutils.bean.IBean;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by weilongzhang on 17/1/10.
 */

public abstract class BeanDao<T extends IBean> {

    private SQLiteDatabase database;
    private ContentValues contentValues;
    protected HashMap<String, String> fieldHashMap = new HashMap<String, String>();

    private void startDatabase(T t) {
        database = DbUtilsFactory.getInstance().getSqlLiteDatabase();
        database.beginTransaction();
        contentValues = createContentValues(t);
    }

    private void endDatabase() {
        database.endTransaction();
    }
    public long save(T t) {
        startDatabase(t);
        long index = database.insert(t.getClass().getName(), null, contentValues);
        endDatabase();
        return index;
    }

    public long update(T t) {
        startDatabase(t);
        long index = database.update(t.getClass().getName(), contentValues, null, null);
        endDatabase();
        return index;
    }

    public long delete(T t) {
        startDatabase(t);
        long index = database.delete(t.getClass().getName(), null, null);
        endDatabase();
        return index;
    }

    public List<T> queryByCondition(String sql) {
        database = DbUtilsFactory.getInstance().getSqlLiteDatabase();
        database.beginTransaction();
        database.endTransaction();
        return null;
    }

    public  ContentValues createContentValues(T t) {
        ContentValues contentValues = new ContentValues();
        Set<String> keys = fieldHashMap.keySet();
        Iterator iterator = keys.iterator();
        while (iterator.hasNext()) {
            Class clazz = t.getClass();
            try {
                Field field = clazz.getDeclaredField(fieldHashMap.get(iterator.next()));
                field.setAccessible(true);
                String classType = field.getType().toString();
                int lastIndex = classType.lastIndexOf(".");
                classType = classType.substring(lastIndex + 1);
                Log.d("zwl", "classType:" + classType);
                Object obj = field.get(t);
                if (classType.equals("String")) {
                    String objString = (String)obj;
                    contentValues.put(iterator.next().toString(), objString);
                } else if (classType.equals("int")) {
                    int objInt = (int)obj;
                    contentValues.put(iterator.next().toString(), objInt);
                } else if (classType.equals("boolean")) {
                    boolean objBoolean = (boolean)obj;
                    contentValues.put(iterator.next().toString(), objBoolean);
                } else {
                    otherType(contentValues);
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return contentValues;
    }

    public abstract void otherType(ContentValues contentValues);

    public String createSql(T t) {
        DbTable dbTable = t.getClass().getAnnotation(DbTable.class);
        if (dbTable != null) {
            StringBuffer sql = new StringBuffer();
            String dbTableName = "";
            if (TextUtils.isEmpty(dbTable.value())) {
                dbTableName = t.getClass().getName();
            } else {
                dbTableName = dbTable.value();
            }

            sql.append("create table if not exists " + dbTableName + " (");

            Field[] fields = t.getClass().getDeclaredFields();
            for (Field field : fields) {
                DbField dbField = field.getAnnotation(DbField.class);
                if (dbField != null) {
                    if (TextUtils.isEmpty(dbField.value())) {
                        sql.append(field.getName());
                        fieldHashMap.put(field.getName(), field.getName());
                    } else {
                        sql.append(dbField.value());
                        fieldHashMap.put(dbField.value(), field.getName());
                    }
                    sql.append(field.getType().getName()).append(",");
                }
            }
            sql.replace(sql.length() - 1, sql.length(), ")");
            Log.d("zwl", sql.toString());
            return sql.toString();
        }
        return null;
    }

    public synchronized void init(Class clazz, Class cls) {

    }
}
