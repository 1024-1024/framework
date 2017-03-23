package com.zwl.designpattern.clone;

/**
 * Created by weilongzhang on 17/3/23.
 */

public class Professor implements Cloneable {
    String name;

    public Professor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "name='" + name + '\'' +
                '}';
    }

    public Object clone() {
        Object o = null;
        try {
            o = (Professor)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }
}
