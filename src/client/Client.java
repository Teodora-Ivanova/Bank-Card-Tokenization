package client;
import client.gui.SignFrame;

public class Client {

    public static void main(String[] args) {

        /*Start the client */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignFrame().setVisible(true);
                // comment in the method
                // nz veche
            }
        });
    }
}
