package org.txazo.jvm.attach;

import com.sun.tools.attach.VirtualMachine;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Set;

public class ThreadDumpTest {

    public static void main(String[] args) throws Exception {
        if (args == null || args.length < 1) {
            throw new RuntimeException("pid is missed");
        }

        String pid = args[0];
        VirtualMachine vm = VirtualMachine.attach(pid);

        String agent = vm.getSystemProperties().getProperty("java.home") + "/lib/management-agent.jar";
        vm.loadAgent(agent, "com.sun.management.jmxremote");

        String connectorAddress = vm.getAgentProperties().getProperty("com.sun.management.jmxremote.localConnectorAddress");
        JMXServiceURL serviceURL = new JMXServiceURL(connectorAddress);
        JMXConnector connector = JMXConnectorFactory.connect(serviceURL);
        MBeanServerConnection connection = connector.getMBeanServerConnection();
        ObjectName objectName = new ObjectName(ManagementFactory.THREAD_MXBEAN_NAME);
        Set<ObjectName> mBeans = connection.queryNames(objectName, null);
        for (ObjectName name : mBeans) {
            ThreadMXBean threadBean = ManagementFactory.newPlatformMXBeanProxy(connection, name.toString(), ThreadMXBean.class);
            long threadIds[] = threadBean.getAllThreadIds();
            for (long threadId : threadIds) {
                ThreadInfo threadInfo = threadBean.getThreadInfo(threadId);
                System.out.println("\"" + threadInfo.getThreadName() + "\" " + threadInfo.getThreadState());
            }
        }

        vm.detach();
    }

}
