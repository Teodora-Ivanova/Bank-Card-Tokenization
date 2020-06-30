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
            JOptionPane.showMessageDialog(null, "Critical error occuried.\nTerminating.",
                    "I/O Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void openSocket() {
        try {
            socket = new Socket((String) null, PORT);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No server with port "
                    + PORT + " was found!\nTerminating...",
                    "Server not found",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);

        }
    }

    public void sendMessage(Object obj, String action) {
        try {
            outputStream.writeObject(new Message(obj, action));
        } catch (IOException ex) {
            showMessage("Critical Error Occured", "Terminating",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public Object recieveObj() {
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

//    public CreditCard recieveCard() {
//        Object obj = recieveObj();
//        return (obj == null) ? null : (CreditCard) obj;
//    }
//    
//    public Session recieveSession() {
//        Object obj = recieveObj();
//        return (obj == null) ? null : (Session) obj;
//    }
//    public String recieveAck() {
//        Object obj = recieveObj();
//        return (obj == null) ? null : (String) obj;
//    }
    private void showMessage(String msg, String tittle, int type) {
        JOptionPane.showMessageDialog(null, msg, tittle, type);
    }

    public boolean handleAck(String ack, String username) throws HeadlessException {
//        AckHandler.getAction(ack).getClientAction().run();

        switch (ack) {
            case "Null":
                showMessage("No user found for the session.\nTerminating.", "Critical Error", 0);
                System.exit(2);

            case "Incorrect password":
                showMessage("Incorrect Password!", "Log in failed", JOptionPane.ERROR_MESSAGE);
                break;

            case "Incorrect username":
                showMessage("No user with name \"" + username + "\" was found!\n"
                        + "(Sign Up first)",
                        "Log in failed",
                        JOptionPane.ERROR_MESSAGE);
                break;

            case "Duplicate":
                JOptionPane.showMessageDialog(null, "This username is already taken!",
                        "Log in failed",
                        JOptionPane.ERROR_MESSAGE);
                break;

            case "TokenNotReg":
                showMessage("No card with such token was found",
                        "Unable to find token",
                        JOptionPane.ERROR_MESSAGE);
                break;
            case "TokenRegDenied":
                JOptionPane.showMessageDialog(null, "You dont have rights to register the card!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                break;

            case "CardReadDenied":
                JOptionPane.showMessageDialog(null, "You dont have rights to export the cards!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                break;
            case "LoginExc":
                JOptionPane.showMessageDialog(null, "Fatal login exception occurred.\nTerminating",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(1);

            case "SuccessCardReg":
                JOptionPane.showMessageDialog(null, "Card registred!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                return true;

            case "Export Completed":
                JOptionPane.showMessageDialog(null, "Export completed!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                return true;

            case "Registration successfull":
                JOptionPane.showMessageDialog(null, "Registration successfull!\n"
                        + "Now you can sign in.",
                        "",
                        JOptionPane.INFORMATION_MESSAGE);
                return true;

            case "Sign in success":
                if (username != null) {
                    JOptionPane.showMessageDialog(null, "Welcome, " + username,
                            "Access Granted",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                return true;
            case "Success":
                return true;
        }
        return false;
    }
    
    public boolean handleAck(String ack) {
        return handleAck(ack, null);
    }

    public void close() {
        try {
            outputStream.close();
            inputStream.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}
