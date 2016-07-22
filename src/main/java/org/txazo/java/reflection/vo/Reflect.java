package org.txazo.java.reflection.vo;

import org.txazo.java.reflection.anno.ClassAnno;
import org.txazo.java.reflection.anno.FieldAnno;

/**
 * Reflect
 */
@ClassAnno
public class Reflect extends SuperReflect implements ReflectInterface {

    public static int NUM = 1;

    @FieldAnno(desc = "id")
    private int id;
    private String name;

    public Reflect() {
    }

    public Reflect(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private void privateMethod() {
    }

    public static void setNUM(int NUM) {
        Reflect.NUM = NUM;
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
