package org.txazo.designpattern.behavior.iterator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 迭代器模式
 */
public class Tuple<E> implements Iterable<E> {

    private List<E> elements = new ArrayList<E>();

    public void add(E e) {
        elements.add(e);
    }

    @Override
    public Iterator iterator() {
        return new TupleIterator();
    }

    private class TupleIterator implements Iterator<E> {

        private int last = -1;
        private int cursor = 0;

        private E get(int index) {
            return index >= 0 && index < size() ? elements.get(index) : null;
        }

        private int size() {
            return elements.size();
        }

        @Override
        public E first() {
            return get(0);
        }

        @Override
        public E last() {
            return get(size() - 1);
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E e = get(last = cursor);
            cursor++;
            return e;
        }

        @Override
        public boolean hasNext() {
            return cursor < size();
        }

        @Override
        public void remove() {
            if (last < 0) {
                throw new IllegalStateException();
            }
            elements.remove(cursor = last);
            last = -1;
        }

    }

}
