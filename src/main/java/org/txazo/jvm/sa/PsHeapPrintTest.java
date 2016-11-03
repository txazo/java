package org.txazo.jvm.sa;

import sun.jvm.hotspot.gc_implementation.parallelScavenge.PSOldGen;
import sun.jvm.hotspot.gc_implementation.parallelScavenge.PSYoungGen;
import sun.jvm.hotspot.gc_implementation.parallelScavenge.ParallelScavengeHeap;
import sun.jvm.hotspot.gc_implementation.shared.MutableSpace;
import sun.jvm.hotspot.gc_interface.CollectedHeap;
import sun.jvm.hotspot.memory.Universe;
import sun.jvm.hotspot.oops.HeapPrinter;
import sun.jvm.hotspot.oops.ObjectHeap;
import sun.jvm.hotspot.runtime.VM;
import sun.jvm.hotspot.tools.Tool;

public class PsHeapPrintTest extends Tool {

    @Override
    public void run() {
        VM vm = VM.getVM();
        Universe universe = vm.getUniverse();
        CollectedHeap heap = universe.heap();

        if (heap instanceof ParallelScavengeHeap) {
            ParallelScavengeHeap psHeap = (ParallelScavengeHeap) heap;
            PSYoungGen youngGen = psHeap.youngGen();
            PSOldGen oldGen = psHeap.oldGen();
            MutableSpace edenSpace = youngGen.edenSpace();
            MutableSpace fromSpace = youngGen.fromSpace();
            MutableSpace toSpace = youngGen.toSpace();

            show("ParallelScavengeHeap", psHeap.capacity(), psHeap.used());
            show("  PSYoungGen", youngGen.capacity(), youngGen.used());
            show("    eden space", edenSpace.capacity(), edenSpace.used());
            show("    from space", fromSpace.capacity(), fromSpace.used());
            show("    to space", toSpace.capacity(), toSpace.used());
            show("  PSOldGen", oldGen.capacity(), oldGen.used());
        }

        ObjectHeap objectHeap = vm.getObjectHeap();
        objectHeap.iterate(new HeapPrinter(System.out));
    }

    private static void show(String name, long capacity, long used) {
        System.out.println(name + " " + sizeOf(capacity) + ", " + sizeOf(used) + " used");
    }

    private static String sizeOf(long size) {
        return size / 1024 + "k";
    }

    public static void main(String[] args) {
        new PsHeapPrintTest().execute(args);
    }

}
