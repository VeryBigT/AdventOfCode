package year2023.day9.part1;

import util.StringUtil;

import java.util.List;
import java.util.stream.IntStream;

public class SequenceGuesser {
    public static void main(String[] args) {
        List<String> input = util.FileReader.readFile("src/year2023/day9/input.txt");
        List<int[]> sequences = input.stream()
                .map(StringUtil::stringToIntArray)
                .toList();
        int result = sequences.stream()
                .mapToInt(SequenceGuesser::guessNext)
                .sum();
        System.out.println(result);
    }

    private static int guessNext(int[] sequence) {
        if(IntStream.of(sequence).allMatch(i -> i == 0))
            return 0;
        int[] differences = IntStream.range(1, sequence.length)
                .map(i -> sequence[i] - sequence[i-1])
                .toArray();
        return sequence[sequence.length - 1] + guessNext(differences);
    }
}
