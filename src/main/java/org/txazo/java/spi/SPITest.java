package org.txazo.java.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * SPI(Service Provider Interface)
 *
 * 接口查找服务实现的机制
 */
public class SPITest {

    public static void main(String[] args) {
        ServiceLoader serviceLoader = ServiceLoader.load(Invoker.class);
        for (Iterator<Invoker> iterator = serviceLoader.iterator(); iterator.hasNext(); ) {
            Invoker invoker = iterator.next();
            invoker.invoke();
        }
    }

}
