package org.txazo.java.network.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

    private volatile boolean isRunning = false;
    private int port;
    private ServerSocket serverSocket;

    public TcpServer(int port) {
        this.port = port;
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException("server start failed", e);
        }
    }

    public void start() {
        if (isRunning) {
            throw new RuntimeException("the server is already started");
        }
        isRunning = true;

        System.out.println("server started, listening on port: " + port);

        try {
            while (isRunning) {
                /** 监听连接 */
                Socket socket = serverSocket.accept();
                new Thread(new ServerThread(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        isRunning = false;
    }

    public static void main(String[] args) {
        new TcpServer(6666).start();
    }

}
