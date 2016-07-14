package org.txazo.designpattern.behavior.chain.tomcat.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 职责链模式－数组实现
 */
public class ApplicationFilterChain implements FilterChain {

    public static final int INCREMENT = 10;

    private int n = 0;
    private int pos = 0;
    private Filter[] filters = new Filter[0];

    @Override
    public void doFilter(ServletRequest request, ServletResponse response) throws Exception {
        if (pos < n) {
            Filter filter = filters[pos++];
            if (filter != null) {
                filter.doFilter(request, response, this);
            }
        }
    }

    public void addFilter(Filter filter) {
        for (Filter f : filters) {
            if (f == filter) {
                return;
            }
        }

        if (n == filters.length) {
            Filter[] newFilters = new Filter[n + 10];
            System.arraycopy(filters, 0, newFilters, 0, n);
            filters = newFilters;
        }

        filters[n++] = filter;
    }

}
