package org.txazo.java.network.httpproxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpProxy {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                new Thread(new SocketThread(socket)).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class SocketThread implements Runnable {

        private Socket sourceSocket;
        private Socket destSocket;

        public SocketThread(Socket sourceSocket) {
            this.sourceSocket = sourceSocket;
        }

        @Override
        public void run() {
            try {
                InputStream input = sourceSocket.getInputStream();
                OutputStream output = sourceSocket.getOutputStream();

                destSocket = new Socket("", 80);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
