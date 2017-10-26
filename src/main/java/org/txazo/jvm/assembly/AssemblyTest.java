package org.txazo.jvm.assembly;

/**
 * 打印汇编信息
 */
public class AssemblyTest {

    // VM Args: -server -Xcomp -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:+DebugNonSafepoints -XX:PrintAssemblyOptions=intel -XX:CompileCommand=compileonly,org/txazo/jvm/assembly/AssemblyTest::main
    public static void main(String[] args) {
        Object a = new Object();
        test(a);
    }

    private static Object test(Object b) {
        return b;
    }

}
