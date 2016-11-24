package org.txazo.jmx.agent;

import org.txazo.jmx.mbean.standard.Hello;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class HelloAgent {

    /**
     * VM Args: -Dcom.sun.management.jmxremote.port=9999 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false
     */
    public static void main(String[] args) throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = new ObjectName("org.txazo.jmx.mbean.standard:type=Hello");
        Hello hello = new Hello(1, "hello", 25);
        mbs.registerMBean(hello, objectName);
        System.out.println("mbean registered ...");
        System.in.read();
    }

}
