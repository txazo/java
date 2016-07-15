package org.txazo.designpattern.structural.adapter.inherit;

import org.txazo.designpattern.structural.adapter.HibernateTemplate;
import org.txazo.designpattern.structural.adapter.JdbcTemplate;

/**
 * 继承适配器
 */
public class HibernateTemplateAdapter<T> extends HibernateTemplate<T> implements JdbcTemplate<T> {

    @Override
    public int add(T t) {
        return super.insert(t);
    }

    @Override
    public int remove(T t) {
        return super.delete(t);
    }

    @Override
    public int update(T t) {
        return super.update(t);
    }

    @Override
    public T get(int id) {
        return super.select(id);
    }

}
