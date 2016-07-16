package org.txazo.designpattern.structural.proxy.staticproxy;

import org.txazo.designpattern.structural.proxy.IService;

/**
 * 组合实现静态代理
 */
public class IServiceCompositionProxy implements IService {

    private IService iService;

    public IServiceCompositionProxy(IService iService) {
        this.iService = iService;
    }

    @Override
    public void service() {
        System.out.println("Proxy before");
        iService.service();
        System.out.println("Proxy after");
    }

}
