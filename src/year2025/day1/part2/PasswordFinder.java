package year2025.day1.part2;

import util.FileReader;
import util.MathUtil;
import util.Pos;

import java.util.List;

public class PasswordFinder {
    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2025/day1/input.txt");
        final int[] state = {50};
        int result = input.stream()
                //.peek(System.out::print)
                .mapToInt(line -> (line.charAt(0) == 'L' ? -1 : 1) * Integer.parseInt(line.substring(1)))
                .map(i -> {
                    int numOf0crossings = Math.abs(i / 100);
                    if ((state[0] != 0 && i % 100 + state[0] <= 0) || i % 100 + state[0] >= 100)
                        numOf0crossings++;
                    state[0] = MathUtil.mod(state[0] + i, 100);
                    return numOf0crossings;
                })
                //.peek(i -> System.out.println(" -> " + state[0] + "," + i))
                .sum();
        System.out.println(result);
    }
}
