package year2024.day1.part2;

import util.CountMap;
import util.FileReader;

import java.util.List;

public class SimilarityScore {
    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2024/day1/input.txt");
        CountMap<Integer> leftCounts = new CountMap<>(), rightCounts = new CountMap<>();
        input.stream().map(s -> s.split("   "))
                .forEach(arr -> { leftCounts.add(Integer.parseInt(arr[0]));
                    rightCounts.add(Integer.parseInt(arr[1])); });
        int sum = leftCounts.keySet().stream()
                .mapToInt(key -> key * leftCounts.count(key) * rightCounts.count(key))
                .sum();
        System.out.println(sum);
    }
}
