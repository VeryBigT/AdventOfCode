package year2025.day2.part2;

import util.io.FileReader;
import util.Range;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.LongStream;

public class IdFinder {
    public static void main(String[] args) {
        long result = 0;
        List<Range> ranges = FileReader.readFile("src/year2025/day2/input.txt", ",").get(0).stream()
                .map(str -> {
                    String[] split = str.split("-");
                    return new Range(Long.parseLong(split[0]), Long.parseLong(split[1]));
                })
                .toList();
        Pattern pattern = Pattern.compile("(\\d+)\\1+");
        for (Range range : ranges) {
            result += LongStream.range(range.start(), range.end() + 1)
                    //.parallel()
                    .filter(l -> pattern.matcher(l + "").matches())
                    .sum();
        }
        System.out.println(result);
    }
}
