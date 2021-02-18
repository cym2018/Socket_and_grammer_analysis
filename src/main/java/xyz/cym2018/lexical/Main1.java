package xyz.cym2018.lexical;

import xyz.cym2018.Client;

public class Main1 {
    public static void main(String[] args) {
        new Thread(new Client("13+26/5*224+3")).start();
    }
}
