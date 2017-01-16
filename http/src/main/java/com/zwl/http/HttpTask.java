package com.zwl.http;


import com.zwl.http.interfaces.IHttpService;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public class HttpTask<T> implements Runnable {
    private IHttpService httpService;
    public HttpTask(RequestHolder<T> requestHolder)
    {
        httpService= requestHolder.getHttpService();
        httpService.setHttpListener(requestHolder.getHttpListener());
        httpService.setUrl(requestHolder.getUrl());
        T request= requestHolder.getRequestInfo();
        String requestInfo= request.toString();

        try {
            httpService.setRequestData(requestInfo.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        httpService.execute();
    }
}
