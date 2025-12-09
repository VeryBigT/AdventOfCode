package year2025.day3.part1;

import util.FileReader;

import java.util.Arrays;

public class JoltageFinder {
    public static void main(String[] args) {
        int[][] input = FileReader.readFileAsIntArray("src/year2025/day3/input.txt");
        int result = Arrays.stream(input)
                //.parallel()
                .mapToInt(JoltageFinder::getJoltage)
                .sum();
        System.out.println(result);
    }

    private static int getJoltage(int[] arr) {
        int firstIdx = 0, firstDigit = 0;
        for (int i = 0; i < arr.length - 1; i++)
            if (arr[i] > firstDigit) {
                firstDigit = arr[i];
                firstIdx = i;
            }
        int secondDigit = arr[firstIdx + 1];
        for (int i = firstIdx + 1; i < arr.length; i++)
            if (arr[i] > secondDigit) {
                secondDigit = arr[i];
            }
        return 10 * firstDigit + secondDigit;
    }
}
