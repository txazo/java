package org.txazo.jvm.sa;

import sun.jvm.hotspot.oops.InstanceKlass;
import sun.jvm.hotspot.tools.jcore.ClassFilter;

public class ClassDumpFilter implements ClassFilter {

    @Override
    public boolean canInclude(InstanceKlass instanceKlass) {
        String className = instanceKlass.getName().toString();
        System.out.println(className);
        return className.equals("org/txazo/jvm/sa/RuntimeClass");
    }

}
