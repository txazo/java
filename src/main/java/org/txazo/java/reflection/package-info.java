/**
 * package-info
 * <p>
 * 1. 包注释
 * 2. 包注解
 * 3. 包类和包常量
 */
@PkgAnno
package org.txazo.java.reflection;

import org.txazo.java.reflection.anno.PkgAnno;

/**
 * 包类
 */
class PkgClass {

    public int pkg() {
        return 1;
    }

}

/**
 * 包常量
 */
class PkgConstant {

    public static final int NUM = 1;

}
