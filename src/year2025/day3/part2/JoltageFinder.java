package year2025.day3.part2;

import util.FileReader;

import java.util.Arrays;

public class JoltageFinder {
    public static void main(String[] args) {
        int[][] input = FileReader.readFileAsIntArray("src/year2025/day3/input.txt");
        long result = Arrays.stream(input)
                //.parallel()
                .mapToLong(JoltageFinder::getJoltage)
                .sum();
        System.out.println(result);
    }

    private static long getJoltage(int[] arr) {
        int idx = -1;
        int[] digits = new int[12];
        for (int i = 0; i < 12; i++) {
            for (int j = idx + 1; j < arr.length - (11 - i); j++) {
                if (arr[j] > digits[i]) {
                    digits[i] = arr[j];
                    idx = j;
                }
            }
        }
        return Arrays.stream(digits)
                .boxed().mapToLong(i -> i)
                .reduce(0, (a, b) -> a * 10 + b);
    }
}
