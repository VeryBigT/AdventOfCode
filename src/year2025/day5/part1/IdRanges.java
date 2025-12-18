package year2025.day5.part1;

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
        List<Long> ids = new ArrayList<>();
        for (; i < input.size(); i++) {
            ids.add(Long.parseLong(input.get(i)));
        }
        int result = (int) ids.stream()
                .filter(id -> Range.contains(ranges, id))
                .count();
        System.out.println(result);
    }
}
