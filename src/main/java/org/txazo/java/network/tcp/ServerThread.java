package org.txazo.java.network.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerThread implements Runnable {

    private volatile boolean isRunning = false;
    private Socket socket;
    private BufferedReader input;
    private PrintStream output;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            if (isRunning) {
                throw new RuntimeException("the server is already started");
            }
            isRunning = true;

            System.out.println("新的连接");

            /** 获取输入输出流 */
            input = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            output = new PrintStream(socket.getOutputStream(), true, "UTF-8");

            /** 发送接受数据 */
            while (isRunning) {
                String data = input.readLine();
                System.out.println(data);
                if (data.equals("connect")) {
                    output.println("connect ok");
                } else if (data.equals("send")) {
                    output.println("reply");
                } else if (data.equals("close")) {
                    output.println("close ok");
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

}
