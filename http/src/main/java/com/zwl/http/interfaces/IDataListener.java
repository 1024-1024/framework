package com.zwl.http.interfaces;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public interface IDataListener<M> {
    /**
     * 回调结果给调用层
     * @param m
     */
     void onSuccess(M m);


      void onFail();
}
