package org.txazo.designpattern.behavior.iterator;

public interface Iterator<E> {

    public E first();

    public E last();

    public E next();

    public boolean hasNext();

    public void remove();

}
