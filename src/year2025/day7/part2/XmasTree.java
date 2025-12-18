package year2025.day7.part2;

import util.io.FileReader;

import java.util.HashMap;
import java.util.Map;

public class XmasTree {
    public static void main(String[] args) {
        char[][] input = FileReader.readFileAsCharArray("src/year2025/day7/output.txt");
        int rows = input[0].length;
        Map<Integer, Long> oldMap = new HashMap<>();
        for (int i = 0; i < rows; i++) {
            if (input[input.length-2][i] == '|');
            oldMap.put(i, 1L);
        }
        for (int i = input.length - 3; i > 0; i -= 2) {
            Map<Integer, Long> newMap = new HashMap<>();
            for (int j = 0; j < rows; j++) {
                if (input[i][j] == '|') {
                    if (input[i+1][j] == '^') {
                        newMap.put(j, oldMap.get(j+1) + oldMap.get(j-1));
                    } else if (input[i+1][j] == '|') {
                        newMap.put(j, oldMap.get(j));
                    }
                }
            }
            oldMap = newMap;
        }
        long numberOfTimelines = oldMap.values().stream()
                .mapToLong(Long::longValue)
                .sum();
        System.out.println(numberOfTimelines);
    }
}
