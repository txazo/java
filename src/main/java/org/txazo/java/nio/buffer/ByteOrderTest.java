package org.txazo.java.nio.buffer;

import org.junit.Test;

import java.nio.ByteOrder;

/**
 * 字节顺序
 *
 * 1) 大端: 高位字节保存在内存的低地址
 * 2) 小端: 低位字节保存在内存的高地址
 */
public class ByteOrderTest {

    @Test
    public void test() throws Exception {
        ByteOrder byteOrder = ByteOrder.nativeOrder();
        System.out.println(byteOrder);
    }

}
