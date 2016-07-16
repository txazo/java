package org.txazo.designpattern.structural.proxy.jdk;

import org.junit.Test;
import org.txazo.designpattern.structural.proxy.IService;
import org.txazo.designpattern.structural.proxy.IServiceImpl;

public class JdkProxyTest {

    @Test
    public void test() {
        IService iService = new IServiceImpl();
        IService proxy = new JdkProxy<IService>().getProxy(iService);
        proxy.service();
    }

}
