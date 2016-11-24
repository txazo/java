package org.txazo.jmx.server;

import org.txazo.jmx.mbean.standard.Client;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class ClientAgent {

    /**
     * VM Args: -Dcom.sun.management.jmxremote.port=9999 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false
     */
    public static void main(String[] args) throws Exception {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = new ObjectName("org.txazo.jmx.mbean.standard:type=Client");
        Client client = new Client("ios");
        server.registerMBean(client, objectName);
        System.out.println("mbean registered ...");
        System.in.read();
    }

}
