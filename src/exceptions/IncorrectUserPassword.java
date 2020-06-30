package exceptions;

public class IncorrectUserPassword extends Exception{

    /**
     *
     */
    public IncorrectUserPassword() {
    }

    /**
     *
     * @param message
     */
    public IncorrectUserPassword(String message) {
        super(message);
    }
    
}
