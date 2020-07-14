package primitives;

import org.junit.Test;

public class CardIdTest {

    public CardIdTest() {
    }

    @Test
    public void validateFirstDigitOfCardNumber() throws Exception {
        System.out.println("Valid Credit Cards Numbers");

        CreditCard firstCard = new CreditCard("378282246310005");
        CreditCard secondCard = new CreditCard("4111111111111111");
        CreditCard thirdCard = new CreditCard("4012888888881881");
        CreditCard fourthCard = new CreditCard("5555555555554444");
        CreditCard fifthCard = new CreditCard("6331101999990016");
    }
}
