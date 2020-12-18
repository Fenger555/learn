package nio.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Fenger
 * @date 2020-12-18 17:35
 */
public class ServerSocketLoop {

    static class Server implements Runnable {

        private int port;
        private Executor threadPool;

        public Server(int port) {
            this.port = port;
            this.threadPool = new ThreadPoolExecutor(5, 10, 60L
                    , TimeUnit.SECONDS, new ArrayBlockingQueue<>(20));
        }

        @Override
        public void run() {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                while (!Thread.interrupted()) {
                    threadPool.execute(new Handle(serverSocket.accept()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class Handle implements Runnable {

        private Socket socket;

        public Handle(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            byte[] in = new byte[1024];
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                outputStream = socket.getOutputStream();
                inputStream = socket.getInputStream();
                int len;
                while ((len = inputStream.read(in)) != -1) {
                    outputStream.write(in, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server(9999);
        new Thread(server, "Server").start();
    }

}
