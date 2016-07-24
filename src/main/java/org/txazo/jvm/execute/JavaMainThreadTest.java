package org.txazo.jvm.execute;

import sun.misc.Launcher;

import java.lang.reflect.Method;

/**
 * JVM main线程模拟
 */
public class JavaMainThreadTest {

    public static void main(String[] args) throws Exception {
        String mainClassName = "org.txazo.java.jvm.execute.MainClass";
        String[] mainArgs = new String[0];
        // main线程
        Thread main = new Thread(new JavaMainThread(mainClassName, mainArgs));
        main.setName("main");
        main.setDaemon(false);
        main.setPriority(5);
        // SystemClassLoader
        main.setContextClassLoader(Launcher.getLauncher().getClassLoader());
        main.start();
    }

    private static class JavaMainThread implements Runnable {

        private String mainClassName;
        private String[] mainArgs;

        public JavaMainThread(String mainClassName, String[] mainArgs) {
            this.mainClassName = mainClassName;
            this.mainArgs = mainArgs;
        }

        @Override
        public void run() {
            Object[] param = null;
            Class<?>[] paramTypes = null;
            if (mainArgs != null) {
                param = new Object[1];
                param[0] = mainArgs;
                paramTypes = new Class[1];
                paramTypes[0] = mainArgs.getClass();
            }

            try {
                Class<?> mainClass = Class.forName(mainClassName);
                Method mainMethod = mainClass.getDeclaredMethod("main", paramTypes);
                mainMethod.invoke(null, param);
            } catch (Throwable t) {
                System.err.print("Exception in thread \"main\" ");
                t.printStackTrace();
            }
        }

    }

}
