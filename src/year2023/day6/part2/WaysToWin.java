package year2023.day6.part2;

import util.io.FileReader;

import java.util.List;

public class WaysToWin {
    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2023/day6/input.txt");
        long time = Long.parseLong(
                input.get(0).split(":")[1].replaceAll("\\s+", ""));
        long record = Long.parseLong(
                input.get(1).split(":")[1].replaceAll("\\s+", ""));
        //System.out.println("time: " + time + ", record: " + record);

        long start = System.currentTimeMillis();
        int result = waysToWin(time, record);
        long end = System.currentTimeMillis();
        System.out.println(result);
        System.out.println("calculated in " + (end - start) + "ms");
    }

    public static int waysToWin(long time, long record) {
        int waysToWin = 0;
        for(int t = 1; t < time; ++t) {
            if(t * (time - t) > record)
                waysToWin++;
        }
        return waysToWin;
    }
}
