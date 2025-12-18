package year2024.day11.part1;

import util.io.FileReader;
import util.StringUtil;

import java.util.List;

public class BlinkStones {
    public static void main(String[] args) {
        List<Long> stones = StringUtil.stringToLongList(
                FileReader.readFile("src/year2024/day11/input.txt").get(0));
        for(int blink = 0; blink < 25; blink++) {
            for(int i = 0; i < stones.size(); i++) {
                long z = stones.get(i);
                if(z == 0)
                    stones.set(i, 1L);
                else if(hasEvenLength(z)) {
                    String str = String.valueOf(z);
                    stones.set(i, Long.parseLong(str.substring(str.length() / 2)));
                    stones.add(i, Long.parseLong(str.substring(0, str.length() / 2)));
                    i++;
                }
                else
                    stones.set(i, z * 2024);
            }
        }
        System.out.println(stones.size());
    }

    private static boolean hasEvenLength(long z) {
        return (int) Math.log10(z) % 2 == 1;
    }
}
