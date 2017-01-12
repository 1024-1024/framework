package com.zwl.dbutils.dao;

import java.util.List;

public interface IBaseDao<T> {
    Long insert(T entity);

    int update(T entity, T where);

    List<T> query(T where);

    List<T> query(T where, String oderBy, Integer startIndex, Integer limit);

    int delete(T where);

    int batch(List<T> list);
}
