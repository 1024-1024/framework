package com.zwl.http;

import com.zwl.http.interfaces.IDataListener;
import com.zwl.http.interfaces.IHttpListener;
import com.zwl.http.interfaces.IHttpService;

import java.util.concurrent.FutureTask;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public class Volley {
    /**
     * @param <T> 请求参数类型
     * @param <M> 响应参数类型
     *            暴露给调用层
     */
    public static <T, M> void sendRequest(T requestInfo, String url, Class<M> response,
                                          IDataListener dataListener) {
        RequestHolder<T> requestHolder = new RequestHolder<>();
        requestHolder.setUrl(url);
        IHttpService httpService = new JsonHttpService();
        IHttpListener httpListener = new JsonDealLitener<>(response, dataListener);
        requestHolder.setHttpService(httpService);
        requestHolder.setHttpListener(httpListener);
        HttpTask<T> httpTask = new HttpTask<>(requestHolder);
        try {
            ThreadPoolManager.getInstance().execute(new FutureTask<Object>(httpTask, null));
        } catch (InterruptedException e) {
            dataListener.onFail();
        }
    }

}
