package org.txazo.designpattern.behavior.memeto;

/**
 * 发起人
 */
public class Originator {

    // 状态
    private String state;

    // 创建备忘录对象
    public Memento createMemento() {
        return new Memento(state);
    }

    // 恢复到备忘录存储的状态
    public void restoreMemento(Memento memento) {
        state = memento.getState();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
