package communication;

import java.awt.HeadlessException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class ClientServerCommunication {
    private Socket socket;
    private final int PORT = 8080;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public ClientServerCommunication() {
        try {
            openSocket();
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            showMessage("Critical error occurred.\nTerminating.", "I/O Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void openSocket() {
        try {
            socket = new Socket((String) null, PORT);
        } catch (IOException ex) {
            showMessage("No server with port " + PORT + " was found!\nTerminating...", "Server not found", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public void sendMessage(Object obj, String action) {
        try {
            outputStream.writeObject(new Message(obj, action));
        } catch (IOException ex) {
            showMessage("Critical Error Occurred", "Terminating", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public Object receiveObject() {
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            return inputStream.readObject();
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found");
        }
        return null;
    }

    public boolean handleAck(String ack) {
        return handleAck(ack, null);
    }

    public boolean handleAck(String ack, String username) throws HeadlessException {
        switch (ack) {
            case "Null":
                showMessage("No user found for the session.\nTerminating.",
                        "Critical Error", 0);
                System.exit(2);

            case "Incorrect password":
                showMessage("Incorrect Password!",
                        "Log in failed",
                        JOptionPane.ERROR_MESSAGE);
                break;

            case "Incorrect username":
                showMessage("No user with name \"" + username + "\" was found!\n" + "(Sign Up first)",
                        "Log in failed",
                        JOptionPane.ERROR_MESSAGE);
                break;

            case "Duplicate":
                showMessage("This username is already taken!",
                        "Log in failed",
                        JOptionPane.ERROR_MESSAGE);
                break;

            case "TokenNotReg":
                showMessage("No card with such token was found",
                        "Unable to find token",
                        JOptionPane.ERROR_MESSAGE);
                break;
            case "TokenRegDenied":
                showMessage("You don't have rights to register the card!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                break;

            case "CardReadDenied":
                showMessage("You don't have rights to export the cards!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                break;
            case  "EmptyUserInfo":
                showMessage("Username or password is empty",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                break;
            case "LoginExc":
                showMessage("Fatal login exception occurred.\nTerminating",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(1);

            case "SuccessCardReg":
                showMessage("Card registered!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                return true;

            case "Export Completed":
                showMessage("Export completed!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                return true;

            case "Registration successfull":
                showMessage("Registration successful!\n" + "Now you can sign in.",
                        "",
                        JOptionPane.INFORMATION_MESSAGE);
                return true;

            case "Sign in success":
                if (username != null) {
                    showMessage("Welcome, " + username,
                            "Allowed Access",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                return true;

            case "Success":
                return true;
        }
        return false;
    }

    private void showMessage(String message, String title, int type) {
        JOptionPane.showMessageDialog(null, message, title, type);
    }

    public void closeSocket() {
        try {
            outputStream.close();
            inputStream.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}
