package org.txazo.java.network.httpproxy;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * 简单的Http代理服务器
 */
public class HttpProxy {

    private static final Set<String> METHODS = new HashSet<>(Arrays.asList("GET", "POST", "HEAD", "PUT", "DELETE", "TRACE", "CONNECT", "OPTIONS"));

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                if (socket != null) {
                    new Thread(new ProxyThread(socket)).start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class ProxyThread implements Runnable {

        // 客户端到代理服务器的Socket连接
        private Socket sourceSocket;
        private BufferedReader sourceInput;
        private OutputStream sourceOutput;
        // 代理服务器到目标主机的Socket连接
        private Socket destSocket;
        private InputStream destInput;
        private OutputStream destOutput;

        public ProxyThread(Socket sourceSocket) {
            this.sourceSocket = sourceSocket;
        }

        @Override
        public void run() {
            try {
                sourceInput = new BufferedReader(new InputStreamReader(sourceSocket.getInputStream()));
                sourceOutput = sourceSocket.getOutputStream();

                // 请求行
                String requestLine = sourceInput.readLine();
                if (requestLine == null) {
                    response(400, "Bad Request");
                    return;
                }

                String[] array = requestLine.split("\\s");
                if (array == null || array.length != 3 || !METHODS.contains(array[0])) {
                    response(400, "Bad Request");
                    return;
                }

                String url = array[1];
                int index = url.indexOf("://");
                if (index < 0) {
                    response(400, "Bad Request");
                    return;
                }

                // 解析主机和端口号
                int port = 80;
                String host = url.substring(index + 3, url.indexOf("/", index + 3));
                if ((index = host.indexOf(":")) > 0) {
                    try {
                        host = host.substring(0, index);
                        port = Integer.parseInt(host.substring(index + 1));
                    } catch (Exception e) {
                        response(400, "Bad Request");
                        return;
                    }
                }

                try {
                    // 连接到目标主机
                    destSocket = new Socket(host, port);
                } catch (Exception e) {
                    return;
                }

                destInput = destSocket.getInputStream();
                destOutput = destSocket.getOutputStream();

                // 代理服务器发送Http请求到目标主机
                String line = null;
                System.out.println(requestLine);
                destOutput.write((requestLine + "\r\n").getBytes());
                while ((line = sourceInput.readLine()) != null) {
                    System.out.println(line);
                    destOutput.write((line + "\r\n").getBytes());
                    if ("".equals(line)) {
                        break;
                    }
                }

                // Http请求: 客户端 - 代理服务器 - 目标主机
                Thread request = new Thread(new StreamThread(sourceSocket.getInputStream(), destOutput));
                // Http响应: 目标主机 - 代理服务器 - 客户端
                Thread response = new Thread(new StreamThread(destInput, sourceOutput));

                request.start();
                response.start();

                request.join();
                response.join();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(destOutput);
                IOUtils.closeQuietly(destInput);
                IOUtils.closeQuietly(destSocket);
                IOUtils.closeQuietly(sourceOutput);
                IOUtils.closeQuietly(sourceOutput);
                IOUtils.closeQuietly(sourceSocket);
            }
        }

        private void response(int code, String msg) throws IOException {
            sourceOutput.write(("HTTP/1.1 " + code + " " + msg + "\r\n").getBytes());
            sourceOutput.write("\r\n".getBytes());
        }

    }

    private static class StreamThread implements Runnable {

        private InputStream input;
        private OutputStream output;

        public StreamThread(InputStream input, OutputStream output) {
            this.input = input;
            this.output = output;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    int length = -1;
                    byte[] temp = new byte[1024];
                    while ((length = input.read(temp)) > -1) {
                        output.write(temp, 0, length);
                        System.out.println(new String(temp, 0, length));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
