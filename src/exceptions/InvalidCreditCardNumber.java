package exceptions;

public class InvalidCreditCardNumber extends Exception {

    /**
     *
     */
    public InvalidCreditCardNumber() {
        super();
    }

    /**
     *
     * @param message
     */
    public InvalidCreditCardNumber(String message) {
        super(message);
    }

}
