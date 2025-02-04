package year2023.day7.part1;

import java.util.Arrays;
import java.util.Collections;

public class Hand implements Comparable<Hand> {

    public static final char[] CARD_VALUES = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};

    public enum HAND_TYPE {
        HIGH_CARD(0), ONE_PAIR(1), TWO_PAIR(2), THREE_OF_A_KIND(3), FULL_HOUSE(4), FOUR_OF_A_KIND(5), FIVE_OF_A_KIND(6);
        public final int value;
        HAND_TYPE(int value) {
            this.value = value;
        }
    }

    private final char[] cards;

    private final HAND_TYPE type;

    private final int bet;

    public Hand(String cards, int bet) {
        this.cards = cards.toCharArray();
        this.type = findType(cards.toCharArray());
        this.bet = bet;
    }

    public int getBet() {
        return bet;
    }

    private static HAND_TYPE findType(char[] cards) {
        Integer[] numOfCards = new Integer[CARD_VALUES.length];
        Arrays.fill(numOfCards, 0);
        for(char c : cards) {
            numOfCards[cardToInt(c)]++;
        }
        Arrays.sort(numOfCards, Collections.reverseOrder());

        HAND_TYPE result = HAND_TYPE.HIGH_CARD;
        if(numOfCards[0] == 5)
            result = HAND_TYPE.FIVE_OF_A_KIND;
        else if(numOfCards[0] == 4)
            result = HAND_TYPE.FOUR_OF_A_KIND;
        else if(numOfCards[0] == 3 && numOfCards[1] == 2)
            result = HAND_TYPE.FULL_HOUSE;
        else if(numOfCards[0] == 3)
            result = HAND_TYPE.THREE_OF_A_KIND;
        else if(numOfCards[0] == 2 && numOfCards[1] == 2)
            result = HAND_TYPE.TWO_PAIR;
        else if(numOfCards[0] == 2)
            result = HAND_TYPE.ONE_PAIR;
        return result;
    }

    @Override
    public int compareTo(Hand o) {
        if(this.type != o.type) {
            return this.type.value - o.type.value;
        }
        for(int i = 0; i < cards.length; ++i) {
            int tmp = cardToInt(this.cards[i]) - cardToInt(o.cards[i]);
            if(tmp != 0) return tmp;
        }
        return 0;
    }

    private static int cardToInt(char card) {
        return switch(card) {
            case '2' -> 0;
            case '3' -> 1;
            case '4' -> 2;
            case '5' -> 3;
            case '6' -> 4;
            case '7' -> 5;
            case '8' -> 6;
            case '9' -> 7;
            case 'T' -> 8;
            case 'J' -> 9;
            case 'Q' -> 10;
            case 'K' -> 11;
            case 'A' -> 12;
            default -> -1;
        };
    }

    @Override
    public String toString() {
        return "{" + Arrays.toString(cards) + "," + type +
                ", bet=" + bet + '}';
    }

    public static void main(String[] args) {
        System.out.println(findType("536TJ".toCharArray()));
    }
}
