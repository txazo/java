package org.txazo.designpattern.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合节点
 */
public class Composite extends Component {

    // 子节点
    private List<Component> childs = new ArrayList<Component>();

    public Composite(String name) {
        super(name);
    }

    @Override
    public void notify(String message) {
        for (Component c : childs) {
            c.notify(message);
        }
    }

    @Override
    public void add(Component component) {
        if (component != null && component != this && getParent() == null) {
            component.setParent(this);
            childs.add(component);
        }
    }

    @Override
    public void remove(Component component) {
        if (component != null && childs.remove(component)) {
            component.setParent(null);
        }
    }

    @Override
    public Component index(int index) {
        return index >= 0 && index < childs.size() ? childs.get(index) : null;
    }

    @Override
    public List<Component> list() {
        return childs;
    }

}
