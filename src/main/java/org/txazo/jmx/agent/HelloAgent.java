package org.txazo.jmx.agent;

import org.apache.commons.lang3.ArrayUtils;
import org.txazo.jmx.mbean.standard.Hello;
import org.txazo.jmx.mbean.standard.HelloMBean;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.Arrays;

public class HelloAgent {

    /**
     * VM Args: -Dcom.sun.management.jmxremote.port=9999 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false
     */
    public static void main(String[] args) throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        registerMBean(mbs);
        createMBean(mbs);
        printMBeanInfo(mbs.getMBeanInfo(ObjectName.getInstance("java.lang:type=Memory")));
        printMBeanInfo(mbs.getMBeanInfo(ObjectName.getInstance("java.lang:type=Threading")));
        System.in.read();
    }

    private static void registerMBean(MBeanServer mbs) throws Exception {
        ObjectName name = ObjectName.getInstance("org.txazo.jmx.mbean.standard:type=Hello,name=local1");
        HelloMBean bean = new Hello(1, "local", 25);
        mbs.registerMBean(bean, name);
    }

    private static void createMBean(MBeanServer mbs) throws Exception {
        String className = "org.txazo.jmx.mbean.standard.Hello";
        ObjectName name = ObjectName.getInstance("org.txazo.jmx.mbean.standard:type=Hello,name=local2");
        Object[] params = new Object[]{1, "local", 25};
        String[] signatures = new String[]{"int", "java.lang.String", "int"};
        mbs.createMBean(className, name, params, signatures);
    }

    private static void printMBeanInfo(MBeanInfo mBeanInfo) {
        mBeanInfo.getClassName();
        MBeanConstructorInfo[] constructorInfos = mBeanInfo.getConstructors();
        if (ArrayUtils.isNotEmpty(constructorInfos)) {
            for (MBeanConstructorInfo info : constructorInfos) {
                System.out.println("Constructor: " + info.getName() + buildParameters(info.getSignature()));
            }
        }

        MBeanAttributeInfo[] attributeInfos = mBeanInfo.getAttributes();
        if (ArrayUtils.isNotEmpty(attributeInfos)) {
            for (MBeanAttributeInfo info : attributeInfos) {
                System.out.println("Attribute: [name=" + info.getName() + ", type=" + info.getType() + ", read=" + info.isReadable() + ", write=" + info.isWritable() + "]");
            }
        }

        MBeanOperationInfo[] operationInfos = mBeanInfo.getOperations();
        if (ArrayUtils.isNotEmpty(operationInfos)) {
            for (MBeanOperationInfo info : operationInfos) {
                System.out.println("Operation: " + info.getReturnType() + " " + info.getName() + buildParameters(info.getSignature()));
            }
        }

        MBeanNotificationInfo[] notificationInfos = mBeanInfo.getNotifications();
        if (ArrayUtils.isNotEmpty(notificationInfos)) {
            for (MBeanNotificationInfo info : notificationInfos) {
                System.out.println("Notification: " + info.getName() + " " + Arrays.toString(info.getNotifTypes()));
            }
        }
    }

    private static String buildParameters(MBeanParameterInfo[] parameterInfos) {
        if (ArrayUtils.isEmpty(parameterInfos)) {
            return "()";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < parameterInfos.length; i++) {
            if (i == 0) {
                sb.append(parameterInfos[i].getType());
            } else {
                sb.append(", ").append(parameterInfos[i].getType());
            }
        }
        sb.append(")");
        return sb.toString();
    }

}
