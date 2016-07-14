package org.txazo.designpattern.behavior.chain.tomcat.filter;

import org.junit.Test;

public class FilterTest {

    @Test
    public void testFilter() throws Exception {
        ApplicationFilterChain chain = new ApplicationFilterChain();
        chain.addFilter(new CharacterFilter());
        chain.addFilter(new IPAttackFilter());
        chain.doFilter(null, null);
    }

}
