package exceptions;


public class DuplicateUserName extends Exception{
    public DuplicateUserName() {
    }

    public DuplicateUserName(String message) {
        super(message);
    }
}
