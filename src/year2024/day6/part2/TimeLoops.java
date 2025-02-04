package year2024.day6.part2;

import util.FileReader;

import java.util.*;
import java.util.stream.Collectors;

public class TimeLoops {
    static int[][] directions = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};

    public static void main(String[] args) {
        char[][] input = FileReader.readFileAsCharArray("src/year2024/day6/input.txt");
        int w = input.length, l = input[0].length;
        int[] dir, pos, startPos = new int[]{0,0}, startDir = new int[]{0,0};
        for(int i = 0; i < w; i++) {
            for(int j = 0; j < l; j++) {
                char c = input[i][j];
                if(c != '.' && c != '#') {
                    startPos = new int[]{i,j};
                    startDir = switch (c) {
                        case '<' -> directions[3];
                        case '^' -> directions[0];
                        case '>' -> directions[1];
                        case 'v' -> directions[2];
                        default -> new int[]{};
                    };
                }
            }
        }

        pos = Arrays.copyOf(startPos, 2);
        dir = Arrays.copyOf(startDir, 2);
        do {
            input[pos[0]][pos[1]] = 'X';
        } while(step(pos, dir, w, l, input));

        int result = 0;
        for(int i = 0; i < w; i++) {
            for (int j = 0; j < l; j++) {
                if(input[i][j] != 'X')
                    continue;

                input[i][j] = '#';
                Map<List<Integer>, List<List<Integer>>> map = new HashMap<>();
                pos = Arrays.copyOf(startPos, 2);
                dir = Arrays.copyOf(startDir, 2);
                while (true) {
                    List<List<Integer>> dirs = map.get(toIntList(pos));
                    if (dirs == null) {
                        dirs = new ArrayList<>();
                        dirs.add(toIntList(dir));
                        map.put(toIntList(pos), dirs);
                    }
                    else if (dirs.contains(toIntList(dir))) {
                        result++;
                        break;
                    }
                    else
                        dirs.add(toIntList(dir));
                    if(!step(pos, dir, w, l, input))
                        break;
                }
                input[i][j] = 'X';
            }
        }
        System.out.println(result);
    }

    private static List<Integer> toIntList(int[] arr) {
        return Arrays.stream(arr)
                .boxed()
                .collect(Collectors.toList());
    }

    private static boolean step(int[] pos, int[] dir, int w, int l, char[][] input) {
        pos[0] += dir[0];
        pos[1] += dir[1];
        if(!(pos[0] >= 0 && pos[0] < w && pos[1] >= 0 && pos[1] < l))
            return false;
        if(input[pos[0]][pos[1]] == '#') {
            pos[0] -= dir[0];
            pos[1] -= dir[1];
            if(Arrays.equals(dir, directions[0])) {
                dir[0] = 0; dir[1] = 1;
            }
            else if(Arrays.equals(dir, directions[1])) {
                dir[0] = 1; dir[1] = 0;
            }
            else if(Arrays.equals(dir, directions[2])) {
                dir[0] = 0; dir[1] = -1;
            }
            else {
                dir[0] = -1; dir[1] = 0;
            }
        }
        return true;
    }

    private static void print(char[][] input) {
        for(char[] line : input) {
            System.out.println(Arrays.toString(line));
        }
    }
}
