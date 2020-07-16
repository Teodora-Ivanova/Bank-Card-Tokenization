package primitives;

import exceptions.InvalidCreditCardNumber;
import exceptions.InvalidFirstNumber;
import java.io.Serializable;
import java.util.Random;

public class CreditCard implements Comparable<CreditCard>, Serializable {
    private String cardId;

    public CreditCard(String cardId) throws InvalidCreditCardNumber, InvalidFirstNumber {
        setCardId(cardId);
    }

    private boolean isFirstNumberValid(String number) {
        return number.matches("^[3-6].* $");
    }

    private int[] convertStringToNumerical(String cardId) {
        char[] cardIdArr = cardId.toCharArray();
        int[] result = new int[cardIdArr.length];

        for (int i = 0; i < cardIdArr.length; i++) {
            result[i] = Character.getNumericValue(cardIdArr[i]);
        }

        return result;
    }

    private int sumDoubledDigits(String cardId) {
        int[] cardIdArr = convertStringToNumerical(cardId);
        int result = 0;
        int doubled;

        for (int i = cardIdArr.length - 2; i >= 0; i -= 2) {
            doubled = cardIdArr[i] * 2;
            result += (doubled % 10) + (doubled / 10);
        }
        for (int i = cardIdArr.length - 1; i >= 0; i -= 2) {
            result += cardIdArr[i];
        }

        return result;
    }

    private boolean isLuhnValid(String cardId) {
        return (sumDoubledDigits(cardId) % 10) == 0;
    }


    private void setCardId(String cardId)
            throws InvalidCreditCardNumber, InvalidFirstNumber {
        if (isFirstNumberValid(cardId)) {
            if (isLuhnValid(cardId)) {
                this.cardId = cardId;
            } else {
                throw new InvalidCreditCardNumber();
            }
        } else {
            throw new InvalidFirstNumber();
        }
    }


    public Token tokenize() {
        final int length = cardId.length();
        int[] cardIdArr = convertStringToNumerical(cardId);
        Token token = new Token(length);
        Random rand = new Random();
        int digit;
        int randomIndex;
        int value;

        do {
            digit = rand.nextInt(10);
        } while (isFirstNumberValid(Integer.toString(digit)));
        token.setDigitAt(0, digit);

        for (int i = 1; i < length - 4; i++) {
            do {
                digit = rand.nextInt(10);
            } while (digit == cardIdArr[i]);
            token.setDigitAt(i, digit);
        }

        for (int i = cardId.length() - 4; i < length; i++) {
            token.setDigitAt(i, cardIdArr[i]);
        }

        if (token.sum() % 10 == 0) {
            do {
                randomIndex = 1 + rand.nextInt(12);
            } while ((value = token.getDigitAt(randomIndex)) == 9);

            token.setDigitAt(randomIndex, ++value);
        }

        return token;
    }

    @Override
    public int compareTo(CreditCard other) {
        return this.cardId.compareTo(other.getCardId());
    }

    private String getCardId() {
        return cardId;
    }

    @Override
    public String toString() {
        return cardId;
    }

}
