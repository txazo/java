package org.txazo.java.aop.jdk;

import org.txazo.java.aop.UserService;
import org.txazo.java.aop.UserServiceImpl;

public class JdkProxyTest {

    // VM: -Dsun.misc.ProxyGenerator.saveGeneratedFiles=true
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        JdkProxy<UserService> jdkProxy = new JdkProxy<UserService>(userService);
        UserService userServiceProxy = jdkProxy.getProxy();
        userServiceProxy.addUser("admin");
    }

}
