package year2024.day11.part2;

import util.BigCountMap;
import util.FileReader;
import util.StringUtil;

import java.util.List;

public class BlinkStones {
    public static void main(String[] args) {
        List<Long> input = StringUtil.stringToLongList(
                FileReader.readFile("src/year2024/day11/input.txt").get(0));
        BigCountMap<Long> stones = new BigCountMap<>();
        for(long stone : input) {
            stones.add(stone);
        }
        for(int blink = 0; blink < 75; blink++) {
            BigCountMap<Long> newStones = new BigCountMap<>();
            for(var entry : stones.entrySet()) {
                long stone = entry.getKey();
                long n = entry.getValue();
                if(stone == 0L)
                    newStones.add(1L, n);
                else if(hasEvenLength(stone)) {
                    String str = String.valueOf(stone);
                    newStones.add(Long.parseLong(str.substring(str.length() / 2)), n);
                    newStones.add(Long.parseLong(str.substring(0, str.length() / 2)), n);
                }
                else
                    newStones.add(stone * 2024L, n);
            }
            stones = newStones;
        }
        long result = stones.values().stream()
                .mapToLong(l -> l)
                .sum();
        System.out.println(result);
    }

    private static boolean hasEvenLength(long z) {
        return (int) Math.log10(z) % 2 == 1;
    }
}
