package year2025.day7.part1;

import util.FileReader;
import util.FileWriter;

public class XmasTree {
    public static void main(String[] args) {
        char[][] input = FileReader.readFileAsCharArray("src/year2025/day7/input.txt");
        for (int x = 0; x < input[0].length; x++)
            if (input[0][x] == 'S')
                input[1][x] = '|';
        int numberOfSplits = 0;
        for ( int y = 2; y < input.length; y++) {
            for (int x = 0; x < input[0].length; x++) {
                if (input[y-1][x] == '|') {
                    if (input[y][x] == '^') {
                        input[y][x-1] = '|';
                        input[y][x+1] = '|';
                        numberOfSplits++;
                    }
                    else
                        input[y][x] = '|';
                }
            }
        }
        FileWriter.writeFile("src/year2025/day7/output.txt", input);
        System.out.println(numberOfSplits);
    }
}
