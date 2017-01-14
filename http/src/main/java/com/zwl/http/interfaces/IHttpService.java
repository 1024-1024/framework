package com.zwl.http.interfaces;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

/**
 *获取网络
 */
public interface IHttpService {
    /**
     * 设置url
     * @param url
     */
    void setUrl(String url);

    /**
     * 执行获取网络
     */
    void execute();

    /**
     * 设置处理接口
     * @param httpListener
     */
    void setHttpListener(IHttpListener httpListener);

    /**
     * 设置请求参数
     * String  1
     * byte[]  2
     *
     */
    void setRequestData(byte[] requestData);

}
