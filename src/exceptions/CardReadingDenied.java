package exceptions;

public class CardReadingDenied extends Exception {
    public CardReadingDenied() {
    }

    public CardReadingDenied(String message) {
        super(message);
    }
}
