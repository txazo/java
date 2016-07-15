package org.txazo.designpattern.creational.prototype.cloneable;

/**
 * 学生, 实现Cloneable接口
 */
public class Student implements Cloneable {

    private int id;
    private String name;
    private Teacher teacher;

    public Student() {
    }

    public Student(int id, String name, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
    }

    /**
     * 克隆方法, 引用类型特殊处理
     */
    @Override
    public Student clone() throws CloneNotSupportedException {
        Student student = (Student) super.clone();
        student.setTeacher(teacher.clone());
        return student;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

}
