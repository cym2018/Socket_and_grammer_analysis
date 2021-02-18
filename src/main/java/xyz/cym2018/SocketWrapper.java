package xyz.cym2018;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 封装socket
 */
public class SocketWrapper {
    private final Socket socket;

    /**
     * 根据socket创建对象
     *
     * @param socket socket
     */
    public SocketWrapper(Socket socket) {
        this.socket = socket;
    }

    /**
     * 根据主机地址和端口创建对象
     *
     * @param host host
     * @param port port
     */
    public SocketWrapper(String host, int port) {
        Socket socket;
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
            socket = null;
        }
        this.socket = socket;
    }

    /**
     * 获取socket传递的信息
     *
     * @return message
     */
    public String readMessage() {
        try {
            InputStream inputStream = socket.getInputStream();
            ReadMessage readMessage = new ReadMessage(inputStream);
            return readMessage.getString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 向socket传递信息
     *
     * @param message message
     */
    public void writeMessage(String message) {
        try {
            socket.getOutputStream().write(message.getBytes(StandardCharsets.UTF_8));
            socket.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class ReadMessage {
    final private InputStream inputStream;

    public ReadMessage(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    String getString() {
        StringBuilder stringBuffer = new StringBuilder();
        byte[] chars = new byte[1024];
        int length;
        while (true) {
            try {
                length = inputStream.read(chars);
                stringBuffer.append(new String(chars, 0, length));
                if (length < 1024) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }
}
