package org.txazo.designpattern.behavior.memeto;

/**
 * 备忘录
 */
public class Memento {

    // 备忘录存储的状态
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
