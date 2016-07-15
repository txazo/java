package org.txazo.designpattern.structural.adapter.composition;

import org.junit.Test;
import org.txazo.designpattern.structural.adapter.HibernateTemplate;
import org.txazo.designpattern.structural.adapter.JdbcTemplate;

/**
 * 组合适配器
 */
public class AdapterTest {

    @Test
    public void test() {
        HibernateTemplate hibernateTemplate = new HibernateTemplate();
        JdbcTemplate jdbcTemplate = new HibernateTemplateAdapter(hibernateTemplate);
        jdbcTemplate.add(null);
        jdbcTemplate.remove(null);
        jdbcTemplate.update(null);
        jdbcTemplate.get(1);
    }

}
