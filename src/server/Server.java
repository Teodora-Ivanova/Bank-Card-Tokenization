package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class Server implements Runnable {

    /**
     * The port on which the server would listen.
     */
    protected int serverPort = 8080;

    /**
     * The server socket which the server would receive.
     */
    protected ServerSocket serverSocket = null;

    /**
     *
     */
    protected boolean isStopped = false;

    /**
     *
     * @param port
     */
    public Server(int port) {
        this.serverPort = port;
    }

    public void run() {
        openServerSocket();
        while (true) {
            Socket clientSocket = null;
            try {
                System.out.println("Waiting..");
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(
                        "Error accepting client connection", e);
            }
            new Thread(
                    new WorkerRunnable(clientSocket)
            ).start();
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port" + serverPort, e);
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        new Thread(new Server(8080)).start();
    }
}
