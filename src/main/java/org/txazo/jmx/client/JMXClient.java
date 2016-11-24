package org.txazo.jmx.client;

import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.Set;

public class JMXClient {

    public static void main(String[] args) throws Exception {
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://:9999/jmxrmi");
        JMXConnector jmxc = JMXConnectorFactory.connect(url);
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
        mbsc.getMBeanCount();
        Set<ObjectName> objectNames = mbsc.queryNames(null, null);
        for (ObjectName objectName : objectNames) {
            System.out.println(objectName.getCanonicalName());
        }

        Set<ObjectInstance> objectInstances = mbsc.queryMBeans(null, null);
        for (ObjectInstance objectInstance : objectInstances) {
            System.out.println(objectInstance.getClassName());
        }

        jmxc.close();
    }

}
