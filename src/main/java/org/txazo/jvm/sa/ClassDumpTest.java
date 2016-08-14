package org.txazo.jvm.sa;

import sun.jvm.hotspot.tools.jcore.ClassDump;

public class ClassDumpTest {

    // java -Dsun.jvm.hotspot.tools.jcore.filter=org.txazo.jvm.sa.ClassDumpFilter -Dsun.jvm.hotspot.tools.jcore.outputDir=~/test sun.jvm.hotspot.tools.jcore.ClassDump 2354
    public static void main(String[] args) {
        System.setProperty("sun.jvm.hotspot.tools.jcore.filter", "org.txazo.jvm.sa.ClassDumpFilter");
        System.setProperty("sun.jvm.hotspot.tools.jcore.outputDir", "~/test");
        ClassDump.main(new String[]{"8430"});
    }

}
