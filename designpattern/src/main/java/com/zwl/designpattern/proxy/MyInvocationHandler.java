package com.zwl.designpattern.proxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by weilongzhang on 17/3/26.
 */

public class MyInvocationHandler implements InvocationHandler {

    private Object object;

    public MyInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.d("zwl", "before");
        System.out.print("pppp");
        Object result = method.invoke(object, args);
        Log.d("zwl", "after");
        return result;
    }
}
