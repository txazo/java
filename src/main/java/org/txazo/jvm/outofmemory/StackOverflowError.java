package org.txazo.jvm.outofmemory;

public class StackOverflowError {

    private int count;

    public void addCount() {
        count++;
        addCount();
    }

    public int getCount() {
        return count;
    }

    /**
     * 栈内存溢出
     * <p>
     * VM Args: -server -verbose:gc -Xms20M -Xmx20M -Xss256K -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintCommandLineFlags
     * stack deepth: 2094
     * <p>
     * VM Args: -server -verbose:gc -Xms20M -Xmx20M -Xss5M -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintCommandLineFlags
     * stack deepth: 282795
     */
    public static void main(String[] args) {
        StackOverflowError stackOverflow = new StackOverflowError();
        try {
            stackOverflow.addCount();
        } catch (java.lang.StackOverflowError e) {
            System.err.println("java.lang.StackOverflowError: stack deepth " + stackOverflow.getCount());
        }
    }

}
