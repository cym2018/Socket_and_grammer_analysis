package xyz.cym2018;

import xyz.cym2018.lexical.WordHandle;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 启动服务,监听端口
 */
public class Server implements Runnable {
    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(80)) {
            //noinspection InfiniteLoopStatement
            while (true) {
                new Thread(new RequestHandle(serverSocket.accept())).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 处理请求信息
 */
class RequestHandle implements Runnable {
    @SuppressWarnings("FieldCanBeLocal")
    private final String SEG = ",";
    private final SocketWrapper socketWrapper;

    RequestHandle(Socket socket) {
        this.socketWrapper = new SocketWrapper(socket);
    }

    @Override
    public void run() {
        try {
            calculate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("handle exception");
            socketWrapper.writeMessage("error input");
        }
    }

    void calculate() {
        String message = socketWrapper.readMessage();
        String ret = WordHandle.getWords(message).toString();
        socketWrapper.writeMessage(ret);
    }
}