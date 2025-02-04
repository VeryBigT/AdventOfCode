package year2024.day6.part1;

import util.FileReader;

import java.util.Arrays;

public class VisitedPositions {

    static int[][] directions = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};

    public static void main(String[] args) {
        char[][] input = FileReader.readFileAsCharArray("src/year2024/day6/input.txt");
        int w = input.length, l = input[0].length;
        int[] dir = new int[]{0,0}, pos = new int[]{0,0};
        for(int i = 0; i < w; i++) {
            for(int j = 0; j < l; j++) {
                char c = input[i][j];
                if(c != '.' && c != '#') {
                    pos = new int[]{i,j};
                    dir = switch (c) {
                        case '<' -> directions[3];
                        case '^' -> directions[0];
                        case '>' -> directions[1];
                        case 'v' -> directions[2];
                        default -> new int[]{};
                    };
                }
            }
        }
        while(true) {
            input[pos[0]][pos[1]] = 'X';
            pos[0] += dir[0];
            pos[1] += dir[1];
            if(!(pos[0] >= 0 && pos[0] < w && pos[1] >= 0 && pos[1] < l))
                break;
            if(input[pos[0]][pos[1]] == '#') {
                pos[0] -= dir[0];
                pos[1] -= dir[1];
                dir = turn(dir);
            }
        }
        int result = 0;
        for (char[] line : input) {
            for (char c : line) {
                if (c == 'X') {
                    result++;
                }
            }
        }
        print(input);
        System.out.println(result);
    }

    private static void print(char[][] input) {
        for(char[] line : input) {
            System.out.println(Arrays.toString(line));
        }
    }

    private static int[] turn(int[] dir) {
        if(Arrays.equals(dir, directions[0]))
            return directions[1];
        if(Arrays.equals(dir, directions[1]))
            return directions[2];
        if(Arrays.equals(dir, directions[2]))
            return directions[3];
        return directions[0];
    }


}
