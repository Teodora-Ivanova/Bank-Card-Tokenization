import client.gui.SignFrame;
import server.Server;

public class Main {

    public static void main(String[] args) {

        new Thread(new Server(8080)).start();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignFrame().setVisible(true);
            }
        });
    }
}
