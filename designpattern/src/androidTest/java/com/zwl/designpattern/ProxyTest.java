package com.zwl.designpattern;

import android.support.test.runner.AndroidJUnit4;

import com.zwl.designpattern.proxy.MyInvocationHandler;
import com.zwl.designpattern.proxy.RealSubject;
import com.zwl.designpattern.proxy.Subject;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Proxy;

/**
 * Created by weilongzhang on 17/3/26.
 */
@RunWith(AndroidJUnit4.class)

public class ProxyTest {

    @Test
    public void dynamicProxyTest() {
        Subject  object = new RealSubject();
        Subject subject = (Subject) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader()
        , RealSubject.class.getInterfaces(), new MyInvocationHandler(object));
        subject.request();
    }

}
