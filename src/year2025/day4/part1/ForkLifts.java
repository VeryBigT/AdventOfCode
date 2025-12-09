package year2025.day4.part1;

import util.FileReader;
import util.Pos;

public class ForkLifts {
    public static void main(String[] args) {
        char[][] input = FileReader.readFileAsCharArray("src/year2025/day4/input.txt");
        int result = 0;
        int maxY = input.length, maxX = input[0].length;
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[y].length; x++) {
                if (input[y][x] != '@')
                    continue;
                Pos pos = new Pos(y, x);
                int neighbouringRolls = 0;
                for (Pos n : pos.allNeighbors()) {
                    if (n.x() >= 0 && n.x() < maxX && n.y() >= 0 && n.y() < maxY && input[n.y()][n.x()] == '@')
                        neighbouringRolls++;
                }
                if (neighbouringRolls < 4)
                    result++;
            }
        }
        System.out.println(result);
    }
}
