package org.txazo.jmx.agent.rmi;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.Iterator;
import java.util.Set;

public class JMXRMIClient {

    public static void main(String[] args) throws Exception {
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/server");
        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

        String domains[] = mbsc.getDomains();
        for (int i = 0; i < domains.length; i++) {
            System.out.println("Domain[" + i + "] = " + domains[i]);
        }

        String domain = mbsc.getDefaultDomain();

        System.out.println("MBean count = " + mbsc.getMBeanCount());

        System.out.println("\nQuery MBeanServer MBeans:");
        Set names = mbsc.queryNames(null, null);
        for (Iterator i = names.iterator(); i.hasNext(); ) {
            System.out.println("ObjectName = " + i.next());
        }
    }

}
