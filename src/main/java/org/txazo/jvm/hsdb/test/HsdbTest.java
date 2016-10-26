package org.txazo.jvm.hsdb.test;

public class HsdbTest {

    private static Hsdb staticHsdb = new Hsdb();
    private Hsdb instanceHsdb = new Hsdb();

    public static void main(String[] args) {
        Hsdb localHsdb = new Hsdb();
    }

}

class Hsdb {
}
