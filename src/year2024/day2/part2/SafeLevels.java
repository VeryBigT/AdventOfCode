package year2024.day2.part2;

import util.io.FileReader;
import util.StringUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class SafeLevels {
    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2024/day2/input.txt");
        List<int[]> reports = input.stream().map(StringUtil::stringToIntArray).toList();
        int result = 0;
        for(int[] report : reports) {
            boolean ascending = report[0] - report[report.length - 1] < 0;
            int[] diff = differences(report, ascending);

            if(Arrays.stream(diff).allMatch(SafeLevels::isSafeStep)) {
                result++;
                continue;
            }
            int badIdx = IntStream.range(0, diff.length).filter(i -> !isSafeStep(diff[i])).findFirst().orElse(-1);
            if(badIdx == diff.length-1) {
                result++;
                continue;
            }
            int[] report1 = deleteAt(report, badIdx), report2 = deleteAt(report, badIdx + 1);
            boolean ascending1 = report1[0] - report1[report1.length - 1] < 0, ascending2 = report2[0] - report2[report2.length - 1] < 0;
            int[] diff1 = differences(report1, ascending1), diff2 = differences(report2, ascending2);
            if(Arrays.stream(diff1).allMatch(SafeLevels::isSafeStep) || Arrays.stream(diff2).allMatch(SafeLevels::isSafeStep)) {
                result++;
            }
        }
        System.out.println(result);
    }

    private static int[] deleteAt(final int[] arr, int i) {
        return IntStream.concat(Arrays.stream(Arrays.copyOfRange(arr, 0, i)),
                Arrays.stream(Arrays.copyOfRange(arr, i + 1, arr.length)))
                .toArray();
    }

    private static int[] differences(final int[] arr, boolean asc) {
        int[] result = new int[arr.length - 1];
        for(int i = 0; i < arr.length - 1; i++) {
            result[i] = asc ? arr[i+1] - arr[i] : arr[i] - arr[i+1];
        }
        return result;
    }

    private static boolean isSafeStep(int diff) {
        return !(diff <= 0 || diff > 3);
    }
}
