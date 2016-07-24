package org.txazo.jvm.collector;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SerialGC {

    // VM Args: -Xms30m -Xmx30m -Xmn10m -XX:+UseSerialGC -XX:+PrintGCDetails
    @Test
    public void testFixSize() throws Exception {
        test();
    }

    // VM Args: -Xms30m -Xmx40m -XX:+UseSerialGC -XX:+PrintGCDetails
    @Test
    public void testExpansionSize() throws Exception {
        test();
    }

    public void test() throws Exception {
        List<Holder> list = new ArrayList<Holder>();
        while (true) {
            list.add(new Holder());
            Thread.sleep(10);
        }
    }

    private static class Holder {

        public byte[] placeHolder = new byte[64 * 1024];

    }

}
