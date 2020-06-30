package exceptions;

public class CardReadingDenied extends Exception{

    /**
     *
     */
    public CardReadingDenied() {
    }

    /**
     *
     * @param message
     */
    public CardReadingDenied(String message) {
        super(message);
    }
    
}
