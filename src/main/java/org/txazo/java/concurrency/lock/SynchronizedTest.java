package org.txazo.java.concurrency.lock;

/**
 * synchronized
 * <p>
 * 1) 偏向锁
 * 2) 轻量级锁
 * 3) 重量级锁
 */
public class SynchronizedTest {

    /**
     * 方法同步(隐式)
     *
     * 1) 方法表结构: ACC_SYNCHRONIZED同步访问标志
     * 2) 自动获取和释放monitor
     */

    /**
     * synchronized同步方法(this)
     */
    public synchronized void synchronizedMethod() {
    }

    /**
     * synchronized静态同步方法(SynchronizedTest.class)
     */
    public static synchronized void synchronizedStaticMethod() {
    }

    /**
     * 代码块同步(显示)
     * <p>
     * 1) monitorenter monitorexit
     */

    private static Object lock = new Object();

    /**
     * synchronized同步代码块(lock)
     */
    public void synchronizedBlock() {
        synchronized (lock) {
        }
    }

//     0: getstatic
//     3: dup
//     4: astore_1
//     5: monitorenter
//     6: aload_1
//     7: monitorexit
//     8: goto          16
//    11: astore_2     Exception Begin(Exception Target)
//    12: aload_1
//    13: monitorexit
//    14: aload_2
//    15: athrow       Exception End
//    16: return       Method Return
//
//    Exception table:
//    from    to  target type
//       6     8    11   any
//      11    14    11   any

}
