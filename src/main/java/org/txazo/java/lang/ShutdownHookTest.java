package org.txazo.java.lang;

/**
 * 虚拟机关闭钩子
 * <p>
 * 应用正常退出
 * ...
 */
public class ShutdownHookTest {

    public static void main(String[] args) throws Exception {
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                System.out.println("Shutdown hook execute");
            }

        });

        Thread.sleep(1000000);
    }

}
