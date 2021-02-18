package xyz.cym2018;

public class Main {
    public static void main(String[] args) {
        new Thread(new Server()).start();
        new Thread(new Client("13+26/5*224+3")).start();
    }
}
