package org.txazo.designpattern.structural.proxy.staticproxy;

import org.junit.Test;
import org.txazo.designpattern.structural.proxy.IService;
import org.txazo.designpattern.structural.proxy.IServiceImpl;

public class StaticProxyTest {

    @Test
    public void testInheritanceProxy() {
        IService proxy = new IServiceInheritanceProxy();
        proxy.service();
    }

    @Test
    public void testCompositionProxy() {
        IService iService = new IServiceImpl();
        IService proxy = new IServiceCompositionProxy(iService);
        proxy.service();
    }

}
