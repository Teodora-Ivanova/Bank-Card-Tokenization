package exceptions;

public class InvalidUsername extends Exception {
    public InvalidUsername() {
    }

    public InvalidUsername(String message) {
        super(message);
    }
}
