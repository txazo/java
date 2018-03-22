package org.txazo.java.aop.jdk;

import org.txazo.java.aop.UserService;
import org.txazo.java.aop.UserServiceImpl;

public class JdkProxyTest {

    // VM: -Dsun.misc.ProxyGenerator.saveGeneratedFiles=true
    public static void main(String[] args) {
        new JdkProxy<UserService>(new UserServiceImpl()).getProxy().getUserName(1000);
    }

}
