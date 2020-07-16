package exceptions;

public class InvalidUsername extends InvalidCreditCardNumber{
    public InvalidUsername() {
    }

    public InvalidUsername(String message) {
        super(message);
    }
}
