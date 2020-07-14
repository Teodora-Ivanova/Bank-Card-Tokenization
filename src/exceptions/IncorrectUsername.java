package exceptions;

public class IncorrectUsername extends Exception {
    public IncorrectUsername() {
    }

    public IncorrectUsername(String message) {
        super(message);
    }
}
