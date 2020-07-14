package communication;

import java.util.HashMap;
import javax.swing.JOptionPane;

public class AckHandler {
    static HashMap<String, Actions> map = new HashMap<>();

    public AckHandler() {
        initialize();
    }

    void initialize() {
        map.put("Duplicate", new Actions(duplicateServer, duplicateClient));
    }

    Runnable duplicateServer = () -> {
        JOptionPane.showMessageDialog(null, "This username is already taken!",
                "Log in failed",
                JOptionPane.ERROR_MESSAGE);
    };

    Runnable duplicateClient = () -> {
        JOptionPane.showMessageDialog(null, "This username is already taken!",
                "Log in failed",
                JOptionPane.ERROR_MESSAGE);
    };

    static Actions getAction(String msg) {
        return map.get(msg);
    }


    class Actions {
        private Runnable serverAction;
        private Runnable clientAction;

        public Actions(Runnable serverAction, Runnable clientAction) {
            this.serverAction = serverAction;
            this.clientAction = clientAction;
        }

        public Runnable getServerAction() {
            return serverAction;
        }

        public Runnable getClientAction() {
            return clientAction;
        }
    }
}
