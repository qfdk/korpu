package me.qfdk.rest.api;

import java.io.Serializable;

/**
 * Created by qfdk on 16/5/10.
 */
public class MyBean {
    private String name;
    private int age;
//
    public MyBean(String name,int age)
    {
        this.name=name;
        this.age=age;
    }

    public MyBean() {

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

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append(name);
        return sb.toString();
    }
}
