package org.txazo.designpattern.structural.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import org.junit.Test;
import org.txazo.designpattern.structural.proxy.IService;
import org.txazo.designpattern.structural.proxy.IServiceImpl;

public class CGLibProxyTest {

    @Test
    public void test() {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/txazo/test");
        IService iService = new IServiceImpl();
        IService proxy = new CGLibProxy().getProxy(iService);
        proxy.service();
    }

}
