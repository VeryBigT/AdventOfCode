package year2023.day4.part2;

import util.FileReader;
import year2023.day4.part1.WinningNumbers;

import java.util.*;
import java.util.stream.Collectors;

public class NumberOfScratchcards {

    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2023/day4/input.txt");
        int[] counter = new int[input.size()];
        int cardNumber = 0;
        for(String line : input) {
            counter[cardNumber]++;
            String[] leftAndRight = line.split(":")[1].split("\\|");
            List<Integer> winningNumbers = Arrays.stream(leftAndRight[0].split(" "))
                    .filter(s -> s.length() != 0)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            List<Integer> yourNumbers = Arrays.stream(leftAndRight[1].split(" "))
                    .filter(s -> s.length() != 0)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            int schnitt = WinningNumbers.schnitt(winningNumbers, yourNumbers);
            for(int i = 1; i <= schnitt; ++i) {
                counter[cardNumber + i] += counter[cardNumber];
            }
            cardNumber++;
        }
        System.out.println(Arrays.stream(counter).sum());
    }
}
