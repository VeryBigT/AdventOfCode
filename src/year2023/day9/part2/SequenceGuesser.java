package year2023.day9.part2;

import util.StringUtil;
import util.io.FileReader;

import java.util.List;
import java.util.stream.IntStream;

public class SequenceGuesser {
    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2023/day9/input.txt");
        List<int[]> sequences = input.stream()
                .map(StringUtil::stringToIntArray)
                .toList();
        int result = sequences.stream()
                .mapToInt(SequenceGuesser::guessPrev)
                .sum();
        System.out.println(result);
    }

    private static int guessPrev(int[] sequence) {
        if(IntStream.of(sequence).allMatch(i -> i == 0))
            return 0;
        int[] differences = IntStream.range(1, sequence.length)
                .map(i -> sequence[i] - sequence[i-1])
                .toArray();
        return sequence[0] - guessPrev(differences);
    }
}
