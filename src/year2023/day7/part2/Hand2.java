package year2023.day7.part2;

import java.util.Arrays;
import java.util.Collections;

public class Hand2 implements Comparable<Hand2> {

    public static final char[] CARD_VALUES = {'J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A'};

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

    public Hand2(String cards, int bet) {
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
        int numOfJ = numOfCards[0];
        numOfCards[0] = 0;
        Arrays.sort(numOfCards, Collections.reverseOrder());
        numOfCards[0] += numOfJ;

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
    public int compareTo(Hand2 o) {
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
            case 'J' -> 0;
            case '2' -> 1;
            case '3' -> 2;
            case '4' -> 3;
            case '5' -> 4;
            case '6' -> 5;
            case '7' -> 6;
            case '8' -> 7;
            case '9' -> 8;
            case 'T' -> 9;
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
        System.out.println(findType("QJJQ2".toCharArray()));
        System.out.println(findType("JKKK2".toCharArray()));
        System.out.println(findType("QQQQ2".toCharArray()));
        System.out.println(findType("2J233".toCharArray()));
    }
}
