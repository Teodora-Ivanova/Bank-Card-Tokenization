package exceptions;

public class InvalidFirstNumber extends Exception{
    public InvalidFirstNumber() {
    }

    public InvalidFirstNumber(String message) {
        super(message);
    }
}
