package org.txazo.java.serialization;

import java.io.Serializable;

public class IChildren extends IParent implements Serializable {

    private static final long serialVersionUID = -6164532894214647621L;

    private String name;

    public IChildren(int id, String name) {
        super(1);
        this.name = name;
    }

}
