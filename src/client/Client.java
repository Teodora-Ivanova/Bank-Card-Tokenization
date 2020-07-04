package client;
import client.gui.SignFrame;

public class Client {

    public static void main(String[] args) {
        // ot tedi
        //test
        /*Start the client */
        // its workin
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignFrame().setVisible(true);
            }
        });
    }
}
