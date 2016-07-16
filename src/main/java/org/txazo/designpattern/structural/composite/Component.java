package org.txazo.designpattern.structural.composite;

import java.util.List;

/**
 * 组件
 */
public abstract class Component {

    private String name;

    // 父节点
    private Component parent = null;

    public Component(String name) {
        this.name = name;
    }

    public abstract void notify(String message);

    public void add(Component component) {
        throw new UnsupportedOperationException();
    }

    public void remove(Component component) {
        throw new UnsupportedOperationException();
    }

    public Component index(int index) {
        throw new UnsupportedOperationException();
    }

    public List<Component> list() {
        throw new UnsupportedOperationException();
    }

    public String getFullName() {
        return parent == null ? getName() : getParent().getFullName() + "-" + getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Component getParent() {
        return parent;
    }

    public void setParent(Component parent) {
        this.parent = parent;
    }

}
