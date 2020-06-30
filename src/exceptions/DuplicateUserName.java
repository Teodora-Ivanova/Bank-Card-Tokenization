package exceptions;


public class DuplicateUserName extends Exception{

    /**
     *
     */
    public DuplicateUserName() {
    }

    /**
     *
     * @param message
     */
    public DuplicateUserName(String message) {
        super(message);
    }
    
}
