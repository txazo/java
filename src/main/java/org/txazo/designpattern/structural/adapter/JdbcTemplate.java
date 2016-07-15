package org.txazo.designpattern.structural.adapter;

/**
 * JDBC模板
 */
public interface JdbcTemplate<T> {

    public int add(T t);

    public int remove(T t);

    public int update(T t);

    public T get(int id);

}
