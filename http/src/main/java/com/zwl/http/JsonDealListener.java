package com.zwl.http;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;
import com.zwl.http.interfaces.IDataListener;
import com.zwl.http.interfaces.IHttpListener;

import org.apache.http.HttpEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public class JsonDealListener<M> implements IHttpListener {
    private Class<M> responseClass;
    private IDataListener<M> dataListener;
    /**
     * 获取主线程的Handle
     * 通过handle切换至主线程
     */
    Handler handler = new Handler(Looper.getMainLooper());

    public JsonDealListener(Class<M> responseClass, IDataListener<M> dataListener) {
        this.responseClass = responseClass;
        this.dataListener = dataListener;
    }

    @Override
    public void onSuccess(HttpEntity httpEntity) {
        InputStream inputStream = null;
        try {
            inputStream = httpEntity.getContent();
            String content = getContent(inputStream);
            final M response = JSON.parseObject(content, responseClass);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    dataListener.onSuccess(response);
                }
            });
        } catch (IOException e) {
            dataListener.onFail();
        }

    }

    @Override
    public void onFail() {
        dataListener.onFail();
    }

    private String getContent(InputStream inputStream) {
        String content = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error=" + e.toString());
                dataListener.onFail();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("Error=" + e.toString());
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            dataListener.onFail();
        }
        return content;
    }
}
