import client.gui.SignFrame;
import server.Server;

public class Main {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        /*Start the server*/
        new Thread(new Server(8080)).start();
       
        /*Start the client */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignFrame().setVisible(true);
            }
        });
    }
}
