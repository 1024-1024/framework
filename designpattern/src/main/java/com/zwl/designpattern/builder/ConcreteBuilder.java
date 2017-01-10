package com.zwl.designpattern.builder;

import android.util.Log;

/**
 * Created by weilongzhang on 17/1/9.
 * 具体构造者1
 */

public class ConcreteBuilder implements AbstractBuilder {

    private Product product = new Product();

    @Override
    public void buildPart1() {
        Log.d("zwl", "buildPart1");
        product.setPart1("part1");
    }

    @Override
    public void buildPart2() {
        Log.d("zwl", "buildPart2");
        product.setPart2(2);
    }

    @Override
    public Product retrieveResult() {
        return product;
    }
}
