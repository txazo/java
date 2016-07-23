package org.txazo.java.concurrency.jmm;

/**
 * 指令重排
 * <p>
 * 1) 编译重排序
 * 2) 指令重排序
 * 3) 内存重排序
 */
public class InstructionRearrangementTest {

    /**
     * 数据依赖性
     *
     * 1) 两个操作访问同一变量，其中有一个为写操作，此时这两个操作之间就存在数据依赖性
     *    写后读
     *    写后写
     *    读后写
     * 2) 单线程中，存在数据依赖性的两个操作不会被重排序
     */

}
