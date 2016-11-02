package org.txazo.jvm.jit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * 逃逸分析 - 栈上分配
 */
public class DoEscapeAnalysisTest {

    public static class Entity {

        private byte[] bytes = new byte[1];

    }

    private void test() {
        Entity entity = new Entity();
        entity.getClass();
    }

    @Benchmark
    public void testDoEscapeAnalysis() {
        test();
    }

    private Entity getEntity() {
        return new Entity();
    }

    @Benchmark
    public void testDonotEscapeAnalysis() {
        getEntity().getClass();
    }

    /**
     * VM Args: -server -verbose:gc -Xms1000M -Xmx1000M -XX:+DoEscapeAnalysis
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(DoEscapeAnalysisTest.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build();
        new Runner(opt).run();
    }

}
