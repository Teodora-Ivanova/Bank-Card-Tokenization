package exceptions;

public class TokenNotRegistered extends Exception {
    public TokenNotRegistered() {
    }

    public TokenNotRegistered(String message) {
        super(message);
    }
}
