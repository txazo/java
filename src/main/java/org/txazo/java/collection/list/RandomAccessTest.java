package org.txazo.java.collection.list;

import org.junit.Test;
import org.txazo.tool.util.watch.StopWatch;
import org.txazo.tool.util.watch.StopWatchTask;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * RandomAccess
 * <pre>
 * 1) List的标记接口, 标识List支持快速随机访问
 * 2) 实现RandomAccess的List遍历时, for (int i = 0, n = list.size(); i < n; i++)性能高于
 *    for (Iterator i = list.iterator(); i.hasNext(); )
 * 3) 原理: Iterator.next()比<List extends RandomAccess>.get()多几步操作
 * 4) RandomAccess的实现类有:
 *    Stack
 *    Vector
 *    ArrayList
 *    CopyOnWriteArrayList
 * </pre>
 */
public class RandomAccessTest {

    @Test
    public void test() throws Throwable {
        int times = 100000;
        final List<Integer> list = new ArrayList<Integer>(1000000);
        for (int i = 0, n = list.size(); i < n; i++) {
            list.add(i);
        }

        StopWatch.newInstance().watch("RandomAccess", times, new StopWatchTask() {

            @Override
            public void execute() throws Throwable {
                for (int i = 0, n = list.size(); i < n; i++) {
                    list.get(i);
                }
            }

        }).watch("Iterator", times, new StopWatchTask() {

            @Override
            public void execute() throws Throwable {
                for (Iterator<Integer> i = list.iterator(); i.hasNext(); ) {
                    i.next();
                }
            }

        }).showTime();
    }

}
