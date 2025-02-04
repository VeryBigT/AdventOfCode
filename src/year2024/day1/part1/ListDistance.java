package year2024.day1.part1;

import util.FileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class ListDistance {
    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2024/day1/input.txt");
        List<Integer> leftList = new ArrayList<>(), rightList = new ArrayList<>();
        input.stream().map(s -> s.split("   "))
                .forEach(arr -> {
                    leftList.add(Integer.parseInt(arr[0]));
                    rightList.add(Integer.parseInt(arr[1]));
                });
        Collections.sort(leftList);
        Collections.sort(rightList);
        int sum = IntStream.range(0, leftList.size())
                .map(i -> Math.abs(rightList.get(i) - leftList.get(i)))
                .sum();
        System.out.println(sum);
    }
}
