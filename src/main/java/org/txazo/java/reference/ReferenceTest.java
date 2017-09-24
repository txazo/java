package org.txazo.java.reference;

/**
 * 引用
 *
 * 引用对象封装了对其它对象的引用
 *
 * 子类
 * ------------------------------------------------------------
 * - SoftReference          软引用
 * - WeakReference          弱引用
 * - PhantomReference       虚引用
 * -
 * - 引用级别: 强引用 > 软引用 > 弱引用 > 虚引用
 * - 强引用    被非Reference对象引用
 * - 软引用    非强引用, 被SoftReference对象引用
 * - 弱引用    非强引用、软引用, 被WeakReference对象引用
 * - 虚引用    非强引用、软引用、虚引用, 被PhantomReference对象引用
 *
 * - 下面两种在jdk内部使用
 * - FinalReference         Final引用
 * -    Finalizer
 * - Cleaner                Cleaner
 * ------------------------------------------------------------
 *
 * Reference的构造函数是package的, 不能在用户代码中被继承, 只能继承它的子类
 *
 * @see java.lang.ref.Reference
 * @see java.lang.ref.Reference.ReferenceHandler
 */
public class ReferenceTest {
}
