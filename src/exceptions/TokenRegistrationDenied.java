package exceptions;

public class TokenRegistrationDenied extends Exception {
    public TokenRegistrationDenied() {
    }

    public TokenRegistrationDenied(String message) {
        super(message);
    }
}
