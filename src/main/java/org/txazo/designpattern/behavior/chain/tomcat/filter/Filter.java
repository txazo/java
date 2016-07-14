package org.txazo.designpattern.behavior.chain.tomcat.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public interface Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws Exception;

}
