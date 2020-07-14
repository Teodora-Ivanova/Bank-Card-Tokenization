package comparators;

import java.util.Comparator;
import primitives.Token;

public class TokenComparator implements Comparator<Token> {
    @Override
    public int compare(Token t1, Token t2) {
        return (t1.compareTo(t2));
    }
}
