package com.zwl.http.interfaces;


import org.apache.http.HttpEntity;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public interface IHttpListener {
    /**
     * 网络访问
     * 处理结果  回调
     * @param httpEntity
     */
    void onSuccess(HttpEntity httpEntity);

    void onFail();
}
