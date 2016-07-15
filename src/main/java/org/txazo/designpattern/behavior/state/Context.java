package org.txazo.designpattern.behavior.state;

/**
 * 上下文环境
 */
public class Context {

    // 状态
    private State state;

    public Context(State state) {
        this.state = state;
    }

    public void request() {
        state.handle();
    }

    // 改变状态
    public void changeState(State state) {
        this.state = state;
    }

}
