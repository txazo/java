package org.txazo.jvm.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class CustomMBeanTest {

    public static void main(String[] args) throws Exception {
        IMBean mBean = new ICustomMBean("test");
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        mBeanServer.registerMBean(mBean, new ObjectName("agent:name=test"));
        Thread.sleep(1000 * 60 * 10);
    }

}
