package client;
import client.gui.SignFrame;

public class Client {

    public static void main(String[] args) {
        //test
        /*Start the client */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignFrame().setVisible(true);
            }
        });
    }
}
