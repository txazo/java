package org.txazo.jmx.mbean.standard;

public class Hello implements HelloMBean {

    private int id;
    private String name;
    private int age;

    public Hello(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void operation() {
        System.out.println("Hello operation ...");
    }

}
