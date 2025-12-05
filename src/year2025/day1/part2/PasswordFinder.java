package year2025.day1.part2;

import util.FileReader;
import util.Pos;

import java.util.List;

public class PasswordFinder {
    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2025/day1/input.txt");
        final int[] state = {50};
        int result = input.stream()
                .mapToInt(line -> (line.charAt(0) == 'L' ? -1 : 1) * Integer.parseInt(line.substring(1)))
                .map(i -> {
                    int numOf0crossings = 0;
                    state[0] += i;
                    if (state[0] == 0)
                        numOf0crossings++;
                    while (state[0] > 99) {
                        state[0] -= 100;
                        numOf0crossings++;
                    }
                    while (state[0] < 0) {
                        state[0] += 100;
                        numOf0crossings++;
                    }
                    return numOf0crossings;
                })
                .sum();
        System.out.println(result);
    }
}
