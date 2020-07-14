package comparators;

import java.util.Comparator;
import java.util.Map;

import primitives.CreditCard;
import primitives.Token;

public class CardComparator implements Comparator<Token> {
    Map<Token, CreditCard> base;

    public CardComparator(Map<Token, CreditCard> base) {
        this.base = base;
    }

    @Override
    public int compare(Token t1, Token t2) {
        int result = base.get(t1).compareTo(base.get(t2));
        if (result <= -1) {
            return -1;
        }
        return 1;
    }
}
