package org.txazo.designpattern.structural.adapter;

/**
 * Hibernate模板
 */
public class HibernateTemplate<T> {

    public int insert(T t) {
        System.out.println("insert");
        return 1;
    }

    public int delete(T t) {
        System.out.println("delete");
        return 1;
    }

    public int update(T t) {
        System.out.println("update");
        return 1;
    }

    public T select(int id) {
        System.out.println("select");
        return null;
    }

}
