package org.txazo.java.io.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedStreamTest {

    public static void main(String[] args) throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream();
        out.connect(in);
        new Thread(new PipedInputThread(in)).start();
        for (int i = 0; ; i++) {
            out.write(i);
            Thread.sleep(10);
        }
    }

    private static class PipedInputThread implements Runnable {

        private PipedInputStream in;

        public PipedInputThread(PipedInputStream in) {
            this.in = in;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    int i = in.read();
                    System.out.println(i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
