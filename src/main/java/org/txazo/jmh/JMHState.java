package org.txazo.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class JMHState {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        volatile double x = Math.PI;

    }

    @State(Scope.Thread)
    public static class ThreadState {

        volatile double x = Math.PI;

    }

    @Benchmark
    public void measureShared(BenchmarkState state) {
        state.x++;
    }

    @Benchmark
    public void measureUnshared(ThreadState state) {
        state.x++;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHState.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .threads(4)
                .forks(1)
                .build();
        new Runner(opt).run();
    }

}
