package org.txazo.java.serialization;

import java.io.Serializable;

public class Teacher implements Serializable {

    private static final long serialVersionUID = 3065959900182294875L;

    private int id;
    private String name;

    public Teacher(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
