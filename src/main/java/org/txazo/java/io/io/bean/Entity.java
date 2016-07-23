package org.txazo.java.io.io.bean;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Entity
 */
public class Entity implements Externalizable {

    private static final long serialVersionUID = 2103808975193669207L;

    private int id;
    private String name;

    public Entity() {
        System.out.println("Entity init");
    }

    public Entity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
        out.writeObject(name);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = (Integer) in.readObject();
        name = (String) in.readObject();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
