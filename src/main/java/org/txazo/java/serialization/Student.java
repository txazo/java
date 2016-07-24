package org.txazo.java.serialization;

import java.io.Serializable;

public class Student implements Serializable {

    private static final long serialVersionUID = 7782253254733071150L;

    private int id;
    private String name;
    private Teacher[] teachers;

    public Student(int id, String name, Teacher[] teachers) {
        this.id = id;
        this.name = name;
        this.teachers = teachers;
    }

}
