package org.txazo.designpattern.behavior.memeto;

/**
 * 备忘录负责人
 */
public class Caretaker {

    // 备忘录
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    // 存储新的备忘录
    public void saveMemento(Memento memento) {
        this.memento = memento;
    }

}
