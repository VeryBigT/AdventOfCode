package year2025.day6.part1;

import util.FileReader;
import util.ListMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MathHomework {
    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2025/day6/input.txt");
        ListMap<Integer, Integer> numbers = new ListMap<>();
        List<Character> operators = new ArrayList<>();
        for (String line : input) {
            if (line.charAt(0) == '+' || line.charAt(0) == '*') {
                operators = Arrays.stream(line.split(" "))
                        .map(String::trim)
                        .filter(s -> !s.isBlank())
                        .map(s -> s.charAt(0)).toList();
                continue;
            }
            int[] nums = Arrays.stream(line.split(" "))
                    .map(String::trim)
                    .filter(s -> !s.isBlank())
                    .mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < nums.length; j++) {
                numbers.add(j, nums[j]);
            }
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
