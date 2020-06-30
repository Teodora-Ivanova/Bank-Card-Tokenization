package comparators;

import java.util.Comparator;
import java.util.Map;
import primitives.CreditCard;
import primitives.Token;

public class CardComparator implements Comparator<Token> {

    Map<Token, CreditCard> base;

    /**
     *
     * @param base
     */
    public CardComparator(Map<Token, CreditCard> base) {
        this.base = base;
    }

    @Override
    public int compare(Token a, Token b) {
        int result = base.get(a).compareTo(base.get(b));
        if (result <= - 1) {
            return -1;
        }
        return 1;
    }
}
