package org.txazo.jvm.instrument;

import java.lang.instrument.Instrumentation;

/**
 * 动态字节码代理
 *
 * @author xztu
 * @date 2017-09-19
 */
public class DynamicBytecodeAgent {

    private static boolean add = false;
    private static Instrumentation inst = null;

    public static void premain(String agentArgs, Instrumentation inst) {
        DynamicBytecodeAgent.inst = inst;

        new Thread(new DynamicBytecodeThread()).start();

        System.out.println("[DynamicBytecodeAgent] started");
    }

    private static class DynamicBytecodeThread implements Runnable {

        @Override
        public void run() {
            for (; ; ) {
                try {
                    Thread.sleep(5000);
                    reload();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void reload() throws Exception {
            if (!add) {
                inst.addTransformer(new HotdeployTransformer(), true);
                add = true;
            }

            System.out.println("[DynamicBytecodeAgent] reloading ...");
            inst.retransformClasses(DynamicClass.class);
            System.out.println("[DynamicBytecodeAgent] reloaded");
        }

    }

}
