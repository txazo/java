package org.txazo.designpattern.structural.composite;

/**
 * 叶子节点
 */
public class Leaf extends Component {

    public Leaf(String name) {
        super(name);
    }

    @Override
    public void notify(String message) {
        System.out.println("Notify to " + getFullName() + " with message: " + message);
    }

}
