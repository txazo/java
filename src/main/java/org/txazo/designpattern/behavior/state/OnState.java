package org.txazo.designpattern.behavior.state;

/**
 * 状态
 */
public class OnState implements State {

    @Override
    public void handle() {
        System.out.println("The state is on");
    }

}
