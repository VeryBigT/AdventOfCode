package year2024.day2.part1;

import util.FileReader;
import util.StringUtil;

import java.util.Arrays;
import java.util.List;

public class SafeLevels {
    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2024/day2/input.txt");
        List<int[]> reports = input.stream().map(StringUtil::stringToIntArray).toList();
        int result = 0;
        for(int[] report : reports) {
            System.out.println(Arrays.toString(report));
            for(int i = 0; i < report.length - 1; i++) {
                report[i] -= report[i+1];
            }
            System.out.println(Arrays.toString(report));
            boolean ascending = report[0] < 0;
            boolean isSafe = true;
            for(int i = 0; i < report.length - 1; i++) {
                int diff = report[i];
                if((ascending ? diff > 0 : diff < 0) || diff == 0 || Math.abs(diff) > 3) {
                    isSafe = false;
                    break;
                }
            }
            System.out.println(isSafe ? "is safe\n" : "is not safe\n");
            if(isSafe)
                result++;
        }
        System.out.println(result);
    }
}
