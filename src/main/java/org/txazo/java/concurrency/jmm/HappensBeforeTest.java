package org.txazo.java.concurrency.jmm;

/**
 * happens-before
 * <p>
 * 1) 同一个线程中的每个Action都happens-before其后的任何一个Action
 * 2) 对一个监视器的解锁happens-before于每一个后续对同一个监视器的加锁
 * 3) 对volatile字段的写入操作happens-before于每一个后续的同一个字段的读操作
 * 4) Thread.start()的调用会happens-before于启动线程里面的动作
 * 5) Thread中的所有动作都happens-before于其他线程检查到此线程结束或者Thread.join（）中返回或者Thread.isAlive()==false
 * 6) 一个线程A调用另一个另一个线程B的interrupt（）都happens-before于线程A发现B被A中断（B抛出异常或者A检测到B的isInterrupted（）或者interrupted()）
 * 7) 一个对象构造函数的结束happens-before与该对象的finalizer的开始
 * 8) 如果A动作happens-before于B动作，而B动作happens-before与C动作，那么A动作happens-before于C动作。
 */
public class HappensBeforeTest {

    /**
     * 解锁               加锁
     * volatile写        volatile读
     * Thread.start()    Thread中的操作
     * Thread中的操作       Thread.join()
     */

}
