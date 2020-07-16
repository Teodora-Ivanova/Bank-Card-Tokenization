package exceptions;

public class SignUpDenied extends Exception {
    public SignUpDenied() {
    }

    public SignUpDenied(String message) {
        super(message);
    }
}
