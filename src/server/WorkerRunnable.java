package server;

import primitives.UserDatabase;
import comparators.*;
import exceptions.*;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import primitives.CreditCard;
import communication.Message;
import primitives.Token;
import primitives.User;

public class WorkerRunnable implements Runnable {

    private Socket clientSocket = null;
    private ObjectInputStream inputStream;

    private UserDatabase db = new UserDatabase();
    private Session session;


    public WorkerRunnable(Socket clientSocket) {
        try {
            inputStream = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException ex) {
            System.out.println("A critical error occuried while"
                    + " trying to open input stream.\nTerminating.");
            System.exit(1);
        }
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        System.out.println("Connection Established!");
        try {
            while (true) {
                try {
                    Message msg = (Message) inputStream.readObject();
                    System.out.println("Recieved message " + msg);
                    processAction(msg);
                } catch (EOFException ex) {
                    System.out.println("EOF");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found!");
        }
    }

    private void processAction(Message msg)
            throws IOException {
        String action = msg.getAction();
        Object obj = msg.getObject();
        Token token;

//        Map<String, Consumer<Session>> map = new HashMap<>();
        /*TODO: USE JAVA 8 AND A MAP.*/
        try {
            switch (action) {
                case "signIn":
                    session = new Session((User) obj);
                    sendAck("Sign in success");
                    sendSession();
                    break;
                case "signUp":
                    db.add((User) obj);
                    sendAck("Registration successfull");
                    break;
                case "Register Card":
                    token = session.registerCard((CreditCard) obj);
                    sendObject(token);
                    break;
                case "Get card":
                    CreditCard card = session.getCardId((Token) obj);
                    sendAck("Success");
                    sendObject(card);
                    break;
                case "Export Tokens":
                    session.exportSorted(new TokenComparator(),
                            new File(session.getUser().getUsername() + "'s_cardsByToken.txt"));
                    sendAck("Export Completed");
                    break;
                case "Export Credit Card":
                    CardComparator comp = new CardComparator(session.getUser().getTokenMap());
                    session.exportSorted(comp,
                            new File(session.getUser().getUsername() + "'s_cardsByCard.txt"));
                    sendAck("Export Completed");
                    break;
                case "Close":
                    System.out.println("Closing session");
                    session.close();
                    System.out.println("Session closed");

            }
        } catch (NullPointerException ex) {
            sendAck("Null");
        } catch (DuplicateUserName ex) {
            sendAck("Duplicate");
        } catch (IncorrectUserPassword ex) {
            sendAck("Incorrect password");
        } catch (IncorrectUsername ex) {
            sendAck("Incorrect username");
        } catch (TokenNotRegistered ex) {
            sendAck("TokenNotReg");
        } catch (TokenRegistrationDenied ex) {
            sendAck("TokenRegDenied");
        } catch (CardReadingDenied ex) {
            sendAck("CardReadDenied");
        } catch (LoginException ex) {
            sendAck("LoginExc");
        } catch (SignUpDenied signUpDenied) {
            sendAck("EmptyUserInfo");
        }


}

    private void sendAck(String ack) {
        System.out.println("Sending Ack");
        sendObject(ack);
        System.out.println("Ack sent");
    }

    private void sendSession() {
        System.out.println("Sending Session");
        sendObject((Session) session);
        System.out.println("Session sent");
    }

    private void sendObject(Object obj) {
        //TODO: dont make new output stream each time!
        try {
            ObjectOutputStream outputStream
                    = new ObjectOutputStream(clientSocket.getOutputStream());
            outputStream.writeObject(obj);
        } catch (IOException ex) {
            System.out.println("Io exc");
            System.out.println(ex);
        }
    }

}
