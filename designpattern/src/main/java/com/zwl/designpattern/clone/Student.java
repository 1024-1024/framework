package com.zwl.designpattern.clone;

/**
 * Created by weilongzhang on 17/3/23.
 */

public class Student implements Cloneable{
    String name;
    int age;
    Professor professor;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name, int age, Professor professor) {
        this.name = name;
        this.age = age;
        this.professor = professor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Object Clone() {
        Student o = null;
        try {
            o = (Student)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        o.professor = (Professor) professor.clone();
        return o;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", professor=" + professor +
                '}';
    }
}
