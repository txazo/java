package org.txazo.java.concurrency.lock;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    @Test
    public void testCondition() {
        Lock lock = new ReentrantLock();

        Condition condition = lock.newCondition();

        lock.lock();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        lock.lock();
        try {
            condition.signal();
        } finally {
            lock.unlock();
        }

        lock.lock();
        try {
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

}
