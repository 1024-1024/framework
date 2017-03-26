package com.zwl.designpattern.proxy;

/**
 * Created by weilongzhang on 17/3/26.
 */

public class ProxyTest implements Subject {

    private Subject subject;

    public ProxyTest(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        subject.request();
    }
}
