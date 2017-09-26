package org.txazo.jvm.assembly;

/**
 * 打印汇编信息
 */
public class AssemblyTest {

    // VM Args: -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:+DebugNonSafepoints -XX:PrintAssemblyOptions=intel -XX:CompileCommand=compileonly,org/txazo/jvm/assembly/AssemblyTest::main
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = a + b;
    }

}
