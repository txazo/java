package org.txazo.designpattern.behavior.chain.tomcat.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class IPAttackFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws Exception {
        System.out.println("IPAttackFilter doFilter before");
        chain.doFilter(request, response);
        System.out.println("IPAttackFilter doFilter after");
    }

}
