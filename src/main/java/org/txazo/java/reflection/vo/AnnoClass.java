package org.txazo.java.reflection.vo;

import org.txazo.java.reflection.anno.*;

/**
 * AnnoClass
 */
@ClassAnno
public class AnnoClass {

    @FieldAnno()
    private int id;

    @ConstructorAnno
    public AnnoClass() {
    }

    @MethodAnno
    public void setId(int id) {
        @LocalAnno String name;
    }

    public void param(@ParamAnno(name = "id") int id, @ParamAnno(name = "name") String name) {
    }

}
