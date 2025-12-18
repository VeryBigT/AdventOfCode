package year2023.day1.part1;

import util.io.FileReader;

import java.util.List;

public class Calibrator {
    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2023/day1/input.txt");
        int sum = 0;
        for(String line : input) {
            sum += firstDigit(line) * 10 + lastDigit(line);
        }
        System.out.println(sum);

        System.out.println(stringToInt2("1abc2"));
        System.out.println(stringToInt2("pqr0stu3vwx"));
        System.out.println(stringToInt2("a1b2c3d4e0f"));
        System.out.println(stringToInt2("xyzabc"));
    }

    private static int lastDigit(String str) {
        char c;
        for(int i = str.length() - 1; i >= 0; --i) {
            c = str.charAt(i);
            if(Character.isDigit(c))
                return c - '0';
        }
        return -1;
    }

    private static int firstDigit(String str) {
        for(char c : str.toCharArray()) {
            if(Character.isDigit(c))
                return c - '0';
        }
        return -1;
    }

    private static int stringToInt(String s) {
        int result = 0;
        int power = 1;
        for(int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                int z = c - '0';
                result += z * power;
                power *= 10;
            }
        }
        return result;
    }

    private static int stringToInt2(String s) {
        String digits = "0";
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                digits += c;
            }
        }
        return Integer.parseInt(digits);
    }
}

