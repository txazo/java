package org.txazo.designpattern.creational.prototype.cloneable;

/**
 * 教师, 实现Cloneable接口
 */
public class Teacher implements Cloneable {

    private int id;
    private String name;

    public Teacher() {
    }

    public Teacher(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 克隆方法
     */
    @Override
    public Teacher clone() throws CloneNotSupportedException {
        return (Teacher) super.clone();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
