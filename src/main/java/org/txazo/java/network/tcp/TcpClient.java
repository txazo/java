package org.txazo.java.network.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class TcpClient {

    private volatile boolean isRunning = false;
    private String host;
    private int port;
    private Socket socket;
    private BufferedReader input;
    private PrintStream output;

    public TcpClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() {
        if (isRunning) {
            throw new RuntimeException("the client is already running");
        }
        isRunning = true;

        try {
            /** 建立连接 */
            socket = new Socket(host, port);

            /** 获取输入输出流 */
            input = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            output = new PrintStream(socket.getOutputStream(), true, "UTF-8");

            output.println("connect");

            /** 发送接受数据 */
            while (isRunning) {
                String data = input.readLine();
                System.out.println(data);
                if (data.equals("connect ok")) {
                    output.println("send");
                } else if (data.equals("reply")) {
                    output.println("close");
                } else if (data.equals("close ok")) {
                    isRunning = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /** 关闭连接 */
            try {
                output.close();
                input.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    new TcpClient("127.0.0.1", 6666).connect();
                }

            }).start();
        }
    }

}
