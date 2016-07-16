package org.txazo.designpattern.structural.facade;

/**
 * 服务员
 */
public class Waiter {

    public void order() {
        System.out.println("点菜");
    }

    public void submitOrder() {
        System.out.println("提交订单");
    }

    public void service() {
        System.out.println("上菜");
    }

}
