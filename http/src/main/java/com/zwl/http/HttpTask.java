package com.zwl.http;


import com.zwl.http.interfaces.IHttpService;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public class HttpTask<T> implements Runnable {
    private IHttpService httpService;
    public HttpTask(RequestHodler<T> requestHodler)
    {
        httpService=requestHodler.getHttpService();
        httpService.setHttpListener(requestHodler.getHttpListener());
        httpService.setUrl(requestHodler.getUrl());
        T request=requestHodler.getRequestInfo();
        String requestInfo= JSON.toJSONString(request);

        try {
            httpService.setRequestData(requestInfo.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        httpService.excute();
    }
}
