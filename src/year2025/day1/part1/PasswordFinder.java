package year2025.day1.part1;

import util.FileReader;
import util.MathUtil;

import java.util.List;

public class PasswordFinder {
    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2025/day1/input.txt");
        final int[] state = {50};
        int result = (int) input.stream()
                .mapToInt(line -> (line.charAt(0) == 'L' ? -1 : 1) * Integer.parseInt(line.substring(1)))
                .map(i -> {
                    state[0] = MathUtil.mod(state[0] + i, 100);
                    return state[0];
                })
                .filter(i -> i == 0)
                .count();
        System.out.println(result);
    }
}
