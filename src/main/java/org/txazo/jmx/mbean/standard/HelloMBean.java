package org.txazo.jmx.mbean.standard;

public interface HelloMBean {

    // 只可读Attribute: id
    public int getId();

    // 可读可写Attribute: name
    public String getName();

    public void setName(String name);

    // 只可写Attribute: age
    public void setAge(int age);

    // Operation
    public void operation();

}
