package xyz.cym2018;

public class Client implements Runnable {
    final String message;

    public Client(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        SocketWrapper socketWrapper = new SocketWrapper("127.0.0.1", 80);
        socketWrapper.writeMessage(message);
        System.out.println(socketWrapper.readMessage());
    }
}
