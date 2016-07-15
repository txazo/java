package org.txazo.designpattern.structural.adapter.traversal;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * Enumeration到Iterator的适配
 */
public class EnumerationAdapter<E> implements Iterator<E> {

    private Enumeration<E> enumeration;

    public EnumerationAdapter(Enumeration<E> enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    @Override
    public E next() {
        return enumeration.nextElement();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
