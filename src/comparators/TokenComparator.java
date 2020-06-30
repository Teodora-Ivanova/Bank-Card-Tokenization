package comparators;

import java.util.Comparator;
import primitives.Token;

public class TokenComparator implements Comparator<Token> {

    @Override
    public int compare(Token a, Token b) {
        return (a.compareTo(b));
    }
}
