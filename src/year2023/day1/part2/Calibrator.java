package year2023.day1.part2;

import java.util.List;

public class Calibrator {
    public static void main(String[] args) {
        List<String> input = util.FileReader.readFile("src/year2023/day1/input.txt");
        int sum = 0;
        for(String line : input) {
            sum += firstDigit(line) * 10 + lastDigit(line);
        }
        System.out.println(sum);
    }

    public static String[] digitStrings = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    private static int firstDigit(String str) {
        int[] idx = new int[10];
        idx[9] = -1;
        for(int i = 0; i < 9; ++i) {
            idx[i] = str.indexOf(digitStrings[i]);
        }
        for(int i = 0; i < str.length(); ++i) {
            if(Character.isDigit(str.charAt(i))) {
                idx[9] = i;
                break;
            }
        }
        int smallestIndex = Integer.MAX_VALUE;
        int indexOfSmallestIndex = -1;
        for(int i = 0; i < 10; ++i) {
            if(idx[i] < smallestIndex && idx[i] >= 0) {
                smallestIndex = idx[i];
                indexOfSmallestIndex = i;
            }
        }
        if(indexOfSmallestIndex < 9)
            return indexOfSmallestIndex + 1;
        return Integer.parseInt(String.valueOf(str.charAt(idx[9])));
    }

    private static int lastDigit(String str) {
        int[] idx = new int[10];
        idx[9] = -1;
        for(int i = 0; i < 9; ++i) {
            idx[i] = str.lastIndexOf(digitStrings[i]);
        }
        for(int i = str.length() - 1; i >= 0; --i) {
            if(Character.isDigit(str.charAt(i))) {
                idx[9] = i;
                break;
            }
        }
        int highestIndex = Integer.MIN_VALUE;
        int indexOfHighestIndex = -1;
        for(int i = 0; i < 10; ++i) {
            if(idx[i] > highestIndex) {
                highestIndex = idx[i];
                indexOfHighestIndex = i;
            }
        }
        if(indexOfHighestIndex < 9)
            return indexOfHighestIndex + 1;
        return Integer.parseInt(String.valueOf(str.charAt(idx[9])));
    }
}

