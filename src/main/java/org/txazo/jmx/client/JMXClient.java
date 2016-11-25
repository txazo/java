package org.txazo.jmx.client;

import org.txazo.jmx.mbean.standard.HelloMBean;

import javax.management.JMX;
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

        // Domain列表
        String[] domains = mbsc.getDomains();
        for (int i = 0; i < domains.length; i++) {
            System.out.println("Domain[" + i + "]: " + domains[i]);
        }

        // 默认Domain
        System.out.println("Default Domain: " + mbsc.getDefaultDomain());

        // 创建MBean
        mbsc.createMBean("org.txazo.jmx.mbean.standard.Hello", ObjectName.getInstance("org.txazo.jmx.mbean.standard:type=Hello,name=remote"), new Object[]{2, "remote", 30}, new String[]{"int", "java.lang.String", "int"});

        // MBean数量
        System.out.println("MBean count: " + mbsc.getMBeanCount());

        // ObjectName列表
        Set<ObjectName> objectNames = mbsc.queryNames(null, null);
        for (ObjectName name : objectNames) {
            System.out.println("ObjectName: " + name.getCanonicalName());
        }

        // ObjectInstance列表
        Set<ObjectInstance> objectInstances = mbsc.queryMBeans(null, null);
        for (ObjectInstance instance : objectInstances) {
            System.out.println("ObjectInstance: " + instance.getObjectName().getCanonicalName() + ", " + instance.getClassName());
        }

        // 创建MBean代理
        HelloMBean helloProxy = JMX.newMBeanProxy(mbsc, ObjectName.getInstance("org.txazo.jmx.mbean.standard:type=Hello,name=local"), HelloMBean.class);
        System.out.println("Hello: id=" + helloProxy.getId() + ", name=" + helloProxy.getName());
        helloProxy.setAge(45);
        helloProxy.operation();

        jmxc.close();
    }

}
