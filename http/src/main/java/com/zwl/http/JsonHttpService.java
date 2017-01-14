package com.zwl.http;


import com.zwl.http.interfaces.IHttpListener;
import com.zwl.http.interfaces.IHttpService;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public class JsonHttpService implements IHttpService {
    private IHttpListener httpListener;

    private HttpClient httpClient=new DefaultHttpClient();
    private HttpPost httpPost;
    private String url;

    private byte[] requestDate;
    /**
     * httpClient获取网络的回调
     */
    private HttpRespnceHandler httpRespnceHandler=new HttpRespnceHandler();
    @Override
    public void setUrl(String url) {
        this.url=url;
    }

    @Override
    public void execute() {
        httpPost=new HttpPost(url);
        ByteArrayEntity byteArrayEntity=new ByteArrayEntity(requestDate);
        httpPost.setEntity(byteArrayEntity);
        try {
            httpClient.execute(httpPost,httpRespnceHandler);
        } catch (IOException e) {
            httpListener.onFail();
        }
    }

    @Override
    public void setHttpListener(IHttpListener httpListener) {
        this.httpListener=httpListener;
    }

    @Override
    public void setRequestData(byte[] requestData) {
         this.requestDate=requestData;
    }
    private class HttpRespnceHandler extends BasicResponseHandler
    {
        @Override
        public String handleResponse(HttpResponse response) throws ClientProtocolException {
            //响应吗
            int code=response.getStatusLine().getStatusCode();
            if(code==200)
            {
                httpListener.onSuccess(response.getEntity());
            }else
            {
                httpListener.onFail();
            }


            return null;
        }
    }
}
