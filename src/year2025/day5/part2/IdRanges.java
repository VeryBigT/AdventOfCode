package year2025.day5.part2;

import util.io.FileReader;
import util.Range;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IdRanges {
    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2025/day5/input.txt");
        Collection<Range> ranges = new ArrayList<>();
        int i = 0;
        String line;
        while (!(line = input.get(i++)).isEmpty()) {
            String[] parts = line.split("-");
            ranges.add(new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1])));
        }
        ranges = Range.combine(ranges);
        long result = ranges.stream()
                .map(Range::elements)
                .reduce(0L, Long::sum);
        System.out.println(result);
    }
}
