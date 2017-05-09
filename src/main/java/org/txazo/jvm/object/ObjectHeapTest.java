package org.txazo.jvm.object;

import sun.jvm.hotspot.oops.*;
import sun.jvm.hotspot.runtime.VM;
import sun.jvm.hotspot.tools.Tool;
import sun.jvm.hotspot.utilities.SystemDictionaryHelper;

public class ObjectHeapTest extends Tool {

    @Override
    public void run() {
        VM vm = VM.getVM();
        ObjectHeap objectHeap = vm.getObjectHeap();
        HeapVisitor heapVisitor = new HeapPrinter(System.out);
        InstanceKlass klass = SystemDictionaryHelper.findInstanceKlass("Debugger$AAA");
        objectHeap.iterateObjectsOfKlass(heapVisitor, klass, false);
        objectHeap.iterate(heapVisitor, new ObjectHeap.ObjectFilter() {

            @Override
            public boolean canInclude(Oop oop) {
                return false;
            }

        });
    }

    /**
     * sudo java -cp .:$JAVA_HOME/lib/sa-jdi.jar:java-1.0.jar org.txazo.jvm.object.ObjectHeapTest pid
     */
    public static void main(String[] args) throws Exception {
        ObjectHeapTest test = new ObjectHeapTest();
        java.lang.reflect.Method start = ObjectHeapTest.class.getDeclaredMethod("start", String[].class);
        start.invoke(test, args);
        test.stop();
    }

}
