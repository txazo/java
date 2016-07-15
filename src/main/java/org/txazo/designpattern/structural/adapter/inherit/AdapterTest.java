package org.txazo.designpattern.structural.adapter.inherit;

import org.junit.Test;
import org.txazo.designpattern.structural.adapter.JdbcTemplate;

/**
 * 继承适配器
 */
public class AdapterTest {

    @Test
    public void test() {
        JdbcTemplate jdbcTemplate = new HibernateTemplateAdapter();
        jdbcTemplate.add(null);
        jdbcTemplate.remove(null);
        jdbcTemplate.update(null);
        jdbcTemplate.get(1);
    }

}
