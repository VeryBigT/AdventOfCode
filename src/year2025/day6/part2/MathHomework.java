package year2025.day6.part2;

import util.FileReader;
import util.ListMap;

import java.util.*;

public class MathHomework {
    public static void main(String[] args) {
        char[][] input = FileReader.readFileAsCharArray("src/year2025/day6/input.txt");
        ListMap<Integer, Integer> numbers = new ListMap<>();
        List<Character> operators = new ArrayList<>();

        int equationIdx = 0;
        for (int x = 0; x < input[0].length; x++) {
            boolean hasDigits = false;
            List<Character> digits = new ArrayList<>();
            for (char[] chars : input) {
                if (x >= chars.length)
                    continue;
                if (Character.isDigit(chars[x])) {
                    digits.add(chars[x]);
                    hasDigits = true;
                } else if (chars[x] == '*' || chars[x] == '+') {
                    operators.add(chars[x]);
                }
            }
            if (hasDigits) {
                int number = digits.stream()
                        .mapToInt(c -> c - '0')
                        .reduce(0, (a, b) -> 10 * a + b);
                numbers.add(equationIdx, number);
            } else
                equationIdx++;
        }

        long result = 0;
        for (int i = 0; i < numbers.size(); i++) {
            if (operators.get(i) == '*')
                result += numbers.get(i).stream()
                        .mapToLong(n -> (long) n)
                        .reduce(1L, (a, b) -> a * b);
            else
                result += numbers.get(i).stream()
                        .mapToInt(Integer::intValue)
                        .sum();
        }
        System.out.println(result);
    }
}
