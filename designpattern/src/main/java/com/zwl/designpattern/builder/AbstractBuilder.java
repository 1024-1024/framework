package com.zwl.designpattern.builder;

/**
 * Created by weilongzhang on 17/1/9.
 *  抽象构造者
 *  1，构造不同的零件
 *  2，返回构造的产品
 */

public  interface AbstractBuilder {
    void buildPart1();
    void buildPart2();
    Product retrieveResult();
}
