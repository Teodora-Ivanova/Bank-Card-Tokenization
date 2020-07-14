package exceptions;

public class IncorrectUserPassword extends Exception {
    public IncorrectUserPassword() {
    }

    public IncorrectUserPassword(String message) {
        super(message);
    }
}
