package year2023.day6.part1;

import util.StringUtil;
import util.io.FileReader;

import java.util.List;

public class WaysToWin {
    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2023/day6/input.txt");
        int[] times = StringUtil.stringToIntArray(input.get(0).split(":")[1]);
        int[] records = StringUtil.stringToIntArray(input.get(1).split(":")[1]);

        int result = 1;
        for(int i = 0; i < times.length; ++i) {
            int waysToWin = waysToWin(times[i], records[i]);
            result *= waysToWin;
        }
        System.out.println(result);
    }

    public static int waysToWin(int time, int record) {
        int waysToWin = 0;
        for(int t = 1; t < time; ++t) {
            if(t * (time - t) > record)
                waysToWin++;
        }
        return waysToWin;
    }
}
