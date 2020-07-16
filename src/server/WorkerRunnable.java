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
        while (true) {
            try {
                Message msg = (Message) inputStream.readObject();
                System.out.println("Recieved message " + msg);
                processAction(msg);
            } catch (EOFException ex) {
                System.out.println("EOF");
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void processAction(Message msg)
            throws Exception {
        String action = msg.getAction();
        Object obj = msg.getObject();
        Token token;


        try {
            switch (action) {
                case "signIn":
                    userSignIn((User) obj);
                    break;
                case "signUp":
                    userSignUp((User) obj);
                    break;
                case "Register Card":
                    userRegisterCard((CreditCard) obj);
                    break;
                case "Get card":
                    CreditCard card = userGetCard((Token) obj);
                    sendObject(card);
                    break;
                case "Export Tokens":
                    exportTokens();
                    break;
                case "Export Credit Card":
                    exportCreditCard();
                    break;
                case "Close":
                    closeSession();

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
        } catch (InvalidUsername invalidUsername) {
            sendAck("invalidUsername");
        }


    }

    private void closeSession() throws SignUpDenied, InvalidUsername {
        System.out.println("Closing session");
        session.close();
        System.out.println("Session closed");
    }

    private void exportCreditCard() throws IOException, CardReadingDenied, TokenNotRegistered {
        CardComparator comp = new CardComparator(session.getUser().getTokenMap());
        session.exportSorted(comp,
                new File(session.getUser().getUsername() + "'s_cardsByCard.txt"));
        sendAck("Export Completed");
    }

    private void exportTokens() throws IOException, CardReadingDenied, TokenNotRegistered {
        session.exportSorted(new TokenComparator(),
                new File(session.getUser().getUsername() + "'s_cardsByToken.txt"));
        sendAck("Export Completed");
    }

    private CreditCard userGetCard(Token obj) throws CardReadingDenied, TokenNotRegistered {
        CreditCard card = session.getCardId(obj);
        sendAck("Success");
        return card;
    }

    private void userRegisterCard(CreditCard obj) throws TokenRegistrationDenied, SignUpDenied, InvalidUsername {
        Token token;
        token = session.registerCard(obj);
        sendObject(token);
    }

    private void userSignUp(User obj) throws IOException, DuplicateUserName {
        db.add(obj);
        sendAck("Registration successfull");
    }

    private void userSignIn(User obj) throws Exception {
        session = new Session(obj);
        sendAck("Sign in success");
        sendSession();
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
