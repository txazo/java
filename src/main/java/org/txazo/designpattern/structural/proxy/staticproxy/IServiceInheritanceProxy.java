package org.txazo.designpattern.structural.proxy.staticproxy;

import org.txazo.designpattern.structural.proxy.IServiceImpl;

/**
 * 继承实现静态代理
 */
public class IServiceInheritanceProxy extends IServiceImpl {

    @Override
    public void service() {
        System.out.println("Proxy before");
        super.service();
        System.out.println("Proxy after");
    }

}
