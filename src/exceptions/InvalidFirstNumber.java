package exceptions;

public class InvalidFirstNumber extends InvalidCreditCardNumber{
    public InvalidFirstNumber() {
    }

    public InvalidFirstNumber(String message) {
        super(message);
    }
}
