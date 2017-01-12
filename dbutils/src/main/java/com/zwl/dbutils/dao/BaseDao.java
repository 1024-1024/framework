package com.zwl.dbutils.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.zwl.dbutils.annotation.DbField;
import com.zwl.dbutils.annotation.DbTable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class BaseDao<T> implements IBaseDao<T> {
    private SQLiteDatabase database;
    private boolean isInit = false;
    private String tableName;
    private Class<T> entityClass;
    private Map<String, Field> cacheFieldMap;

    public synchronized boolean init(Class<T> entity, SQLiteDatabase sqLiteDatabase) {
        if (!isInit) {
            this.database = sqLiteDatabase;
            this.tableName = entity.getAnnotation(DbTable.class).value();
            this.entityClass = entity;
            if (!database.isOpen()) {
                return false;
            }
            if (!TextUtils.isEmpty(createTable())) {
                database.execSQL(createTable());
            }

            cacheFieldMap = new HashMap<>();
            initCacheMap();
            isInit = true;
        }
        return true;
    }

    private void initCacheMap() {
        String sql = "select * from " + this.tableName + " limit 1,0";
        Cursor cursor = null;
        try {
            cursor = this.database.rawQuery(sql, null);
            String[] columnNames = cursor.getColumnNames();
            Field[] columnFields = entityClass.getFields();
            for (Field field : columnFields) {
                field.setAccessible(true);
            }

            for (String columnName : columnNames) {
                Field colmunToFiled = null;
                //开始找对应关系
                for (Field field : columnFields) {
                    String fieldName = null;
                    if (field.getAnnotation(DbField.class) != null) {
                        fieldName = field.getAnnotation(DbField.class).value();
                    } else {
                        fieldName = field.getName();
                    }

                    if (columnName.equals(fieldName)) {
                        colmunToFiled = field;
                        break;
                    }
                }

                if (colmunToFiled != null) {
                    cacheFieldMap.put(columnName, colmunToFiled);
                }
            }
        } catch (Exception e) {

        } finally {
            cursor.close();
        }
    }


    public abstract String createTable();


    @Override
    public Long insert(T entity) {
        Map<String, String> map = getValues(entity);
        ContentValues contentValues = getContentValues(map);
        Long result = database.insert(tableName, null, contentValues);
        return result;
    }

    private ContentValues getContentValues(Map<String, String> map) {
        ContentValues contentValues = new ContentValues();
        Set keys = map.keySet();
        Iterator iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String value = map.get(key);
            if (value != null) {
                contentValues.put(key, value);
            }
        }
        return contentValues;
    }

    private Map<String, String> getValues(T entity) {
        HashMap<String, String> result = new HashMap<>();
        Iterator fieldsIterator = cacheFieldMap.values().iterator();
        while (fieldsIterator.hasNext()) {
            Field colmunToFiled = (Field) fieldsIterator.next();
            String cacheKey = null;
            String cacheVuale = null;
            if (colmunToFiled.getAnnotation(DbField.class) != null) {
                cacheKey = colmunToFiled.getAnnotation(DbField.class).value();
            } else {
                cacheKey = colmunToFiled.getName();
            }
            try {
                if (null == colmunToFiled.get(entity)) {
                    continue;
                }
                cacheVuale = colmunToFiled.get(entity).toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            result.put(cacheKey, cacheVuale);
        }
        return result;
    }

    @Override
    public int update(T entity, T where) {
        int result = -1;
        Map values = getValues(entity);
        Condition condition = new Condition(getValues(where));
        result = database.update(tableName, getContentValues(values), condition.whereClause,
                condition.WhereArgs);
        return result;
    }

    @Override
    public List<T> query(T where) {
        return query(where, null, null, null);
    }

    @Override
    public List<T> query(T where, String oderBy, Integer startIndex, Integer limit) {
        Map values = getValues(where);
        String limitString = null;
        if (startIndex != null && limit != null) {
            limitString = startIndex + " , " + limit;
        }

        Condition condition = new Condition(values);

        Cursor cursor = null;

        cursor = database.query(tableName, null, condition.getWhereClause(), condition
                .getWhereArgs(), null, null, oderBy, limitString);

        List<T> result = getReslut(cursor, where);
        cursor.close();
        return result;
    }

    private List<T> getReslut(Cursor cursor, T where) {
        ArrayList list = new ArrayList();
        Object item;
        /*  遍历游标所持有的数据
            每次遍历一条数据 生成一个对象
            并且添加值 list集合
         */
        while (cursor.moveToNext()) {
            Class<?> clazz = where.getClass();
            try {
                item = clazz.newInstance();
                Iterator iterator = cacheFieldMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, Field> entry = (Map.Entry<String, Field>) iterator.next();
                    String columnName = entry.getKey();
                    Field field = entry.getValue();

                    Integer colmunIndex = cursor.getColumnIndex(columnName);

                    Class type = field.getType();

                    if (colmunIndex != -1) {
                        if (type == String.class) {
                            field.set(item, cursor.getString(colmunIndex));
                        } else if (type == Integer.class) {
                            field.set(item, cursor.getInt(colmunIndex));
                        } else if (type == Long.class) {
                            field.set(item, cursor.getLong(colmunIndex));
                        } else if (type == Double.class) {
                            field.set(item, cursor.getDouble(colmunIndex));
                        } else if (type == byte[].class) {
                            field.set(item, cursor.getBlob(colmunIndex));
                        } else {
                            continue;
                        }
                    }


                }
                list.add(item);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        }
        return list;
    }

    @Override
    public int batch(List<T> list) {


        return 0;
    }

    @Override
    public int delete(T where) {
        Map values = getValues(where);

        Condition condition = new Condition(values);

        int result = database.delete(tableName, condition.getWhereClause(), condition
                .getWhereArgs());

        return result;
    }

    class Condition {
        private String whereClause;
        private String[] WhereArgs;

        public Condition(Map<String, String> map) {
            ArrayList list = new ArrayList();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" 1=1 ");
            Set keys = map.keySet();
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                String value = map.get(key);
                if (value != null) {
                    stringBuilder.append(" and " + key + " =? ");
                    list.add(value);
                }
            }
            this.whereClause = stringBuilder.toString();
            this.WhereArgs = (String[]) list.toArray(new String[list.size()]);
        }

        public String getWhereClause() {
            return whereClause;
        }


        public String[] getWhereArgs() {
            return WhereArgs;
        }
    }

}
