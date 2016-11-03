package org.txazo.jvm.sa.classdump;

import sun.jvm.hotspot.oops.InstanceKlass;
import sun.jvm.hotspot.tools.jcore.ClassFilter;

import java.io.IOException;

public class ClassDumpFilter implements ClassFilter {

    @Override
    public boolean canInclude(InstanceKlass instanceKlass) {
        String klassName = instanceKlass.getName().asString();
        System.out.println("Loaded: " + klassName);
        return klassName.equals("org/txazo/jvm/sa/classdump/ClassDumpFilter");
    }

    public static void main(String[] args) throws IOException {
        System.in.read();
    }

}
