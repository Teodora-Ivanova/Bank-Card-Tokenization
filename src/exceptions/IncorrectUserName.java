package exceptions;

public class IncorrectUserName extends Exception {

    /**
     *
     */
    public IncorrectUserName() {
    }

    /**
     *
     * @param message
     */
    public IncorrectUserName(String message) {
        super(message);
    }

}
