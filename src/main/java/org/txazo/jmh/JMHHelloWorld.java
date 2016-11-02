package org.txazo.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class JMHHelloWorld {

    @Benchmark
    public void test() {
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHHelloWorld.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }

}
