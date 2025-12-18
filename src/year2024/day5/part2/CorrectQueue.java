package year2024.day5.part2;

import util.io.FileReader;
import util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static year2024.day5.part1.PrintQueue.isValid;

public class CorrectQueue {
    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2024/day5/input.txt");
        List<int[]> rules = new ArrayList<>();
        List<int[]> manuals = new ArrayList<>();
        int i;
        for(i = 0;; i++) {
            String line = input.get(i);
            if(line.length() < 5)
                break;
            rules.add(StringUtil.stringToIntArray(line, "\\|"));
        }
        for(i += 1; i < input.size(); i++)
            manuals.add(StringUtil.stringToIntArray(input.get(i), ","));

        int sum = manuals.stream().filter(manual -> !isValid(manual, rules))
                .map(manual -> correct(manual, rules))
                .mapToInt(manual -> manual[manual.length / 2])
                .sum();

        System.out.println(sum);
    }

    private static int[] correct(int[] manual, List<int[]> rules) {
        for(int i = 0; i < manual.length - 1; i++) {
            int page1 = manual[i];
            for(int j = i + 1; j < manual.length; j++) {
                int page2 = manual[j];
                if(rules.stream().anyMatch(rule -> Arrays.equals(rule, new int[]{page2, page1}))) {
                    int tmp = manual[i];
                    manual[i] = manual[j];
                    manual[j] = tmp;
                    return correct(manual, rules);
                }
            }
        }
        return manual;
    }
}
