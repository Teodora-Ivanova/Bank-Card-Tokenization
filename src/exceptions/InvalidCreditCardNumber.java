package exceptions;

public class InvalidCreditCardNumber extends Exception {
    public InvalidCreditCardNumber() {
        super();
    }

    public InvalidCreditCardNumber(String message) {
        super(message);
    }
}
