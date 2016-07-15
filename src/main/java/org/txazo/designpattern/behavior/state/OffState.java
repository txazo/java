package org.txazo.designpattern.behavior.state;

/**
 * 状态
 */
public class OffState implements State {

    @Override
    public void handle() {
        System.out.println("The state is off");
    }

}
