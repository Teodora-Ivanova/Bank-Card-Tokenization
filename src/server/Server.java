package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class Server implements Runnable {


    protected int serverPort = 8080;


    protected ServerSocket serverSocket = null;

    protected boolean isStopped = false;

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


    public static void main(String[] args) {
        new Thread(new Server(8080)).start();
    }
}
