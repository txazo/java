package org.txazo.jvm.sa.classdump;

import sun.jvm.hotspot.oops.InstanceKlass;
import sun.jvm.hotspot.tools.jcore.ClassFilter;

import java.io.IOException;

/**
 * ClassDump
 *
 * sudo java -cp .:$JAVA_HOME/lib/sa-jdi.jar:java-1.0.jar -Dsun.jvm.hotspot.tools.jcore.filter=org.txazo.jvm.sa.classdump.ClassDumpFilter -Dsun.jvm.hotspot.tools.jcore.outputDir=/Users/txazo/test sun.jvm.hotspot.tools.jcore.ClassDump <pid>
 */
public class ClassDumpFilter implements ClassFilter {

    @Override
    public boolean canInclude(InstanceKlass instanceKlass) {
        String klassName = instanceKlass.getName().asString();
        System.out.println("Loaded: " + klassName);
        return klassName.equals("org/txazo/jvm/sa/classdump/ClassDumpFilter");
    }

    public static void main(String[] args) throws IOException {
        System.out.println(System.currentTimeMillis());
        System.in.read();
    }

}
