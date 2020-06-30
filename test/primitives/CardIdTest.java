/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitives;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author a
 */
public class CardIdTest {

    public CardIdTest() {
    }

    @Test
    public void testPlentyOfCards() throws Exception {
        System.out.println("valid cards?");

        CreditCard c1 = new CreditCard("378282246310005");
        CreditCard c2 = new CreditCard("4111111111111111");
        CreditCard c3 = new CreditCard("4012888888881881");
        CreditCard c4 = new CreditCard("5555555555554444");
        CreditCard c5 = new CreditCard("6331101999990016");

    }

}
