package org.txazo.designpattern.structural.adapter.composition;


import org.txazo.designpattern.structural.adapter.HibernateTemplate;
import org.txazo.designpattern.structural.adapter.JdbcTemplate;

/**
 * 组合适配器
 */
public class HibernateTemplateAdapter<T> implements JdbcTemplate<T> {

    private HibernateTemplate<T> hibernateTemplate;

    public HibernateTemplateAdapter(HibernateTemplate<T> hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public int add(T t) {
        return hibernateTemplate.insert(t);
    }

    @Override
    public int remove(T t) {
        return hibernateTemplate.delete(t);
    }

    @Override
    public int update(T t) {
        return hibernateTemplate.update(t);
    }

    @Override
    public T get(int id) {
        return hibernateTemplate.select(id);
    }

}
