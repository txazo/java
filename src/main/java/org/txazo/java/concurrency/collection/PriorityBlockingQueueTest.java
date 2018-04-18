package org.txazo.java.concurrency.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueTest {

    @Test
    public void test() {
        List<Integer> ids = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue();
        Collections.shuffle(ids);
        ids.stream().forEach(i ->
                queue.add(i)
        );
        for (int i = 0; i < ids.size(); i++) {
            System.out.println(queue.poll());
        }
    }

}
