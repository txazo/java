package org.txazo.designpattern.structural.adapter.traversal;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * Iterator到Enumeration的适配
 */
public class IteratorAdapter<E> implements Enumeration<E> {

    private Iterator<E> iterator;

    public IteratorAdapter(Iterator<E> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasMoreElements() {
        return iterator.hasNext();
    }

    @Override
    public E nextElement() {
        return iterator.next();
    }

}
