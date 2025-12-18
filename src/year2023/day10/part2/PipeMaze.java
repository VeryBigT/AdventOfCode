package year2023.day10.part2;

import util.Pos;
import util.StringUtil;
import util.io.FileReader;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PipeMaze {

    public static void main(String[] args) throws InterruptedException {
        char[][] maze = FileReader
                .readFileAsCharArray("src/year2023/day10/input.txt");

        long startTime = System.currentTimeMillis();
        List<Pos> loop = new ArrayList<>();
        Pos next = findStart(maze);
        do {
            loop.add(next);
            next = findNext(maze, next, loop);
        } while(!next.equals(Pos.NULL_POS));
        //System.out.println(loop.size() / 2);

        int result = IntStream.range(0, maze.length)
                .parallel()
                .mapToObj(y -> {
                    Boolean[] arr = new Boolean[maze[0].length];
                    arr[0] = false;
                    for(int x = 1; x < maze[0].length; x++) {
                        Pos pos = new Pos(y, x);
                        if(!loop.contains(pos) && !loop.contains(pos.left()))
                            arr[x] = arr[x-1];
                        else
                            arr[x] = isInside(maze, loop, pos);
                    }
                    return arr;
                })
                .mapToInt(arr -> (int) Arrays.stream(arr)
                        .filter(b -> b)
                        .count())
                .sum();

        long endTime = System.currentTimeMillis();
        System.out.println(result + " calculated in: " + (endTime - startTime) + "ms");
    }

    private static boolean isInside(char[][] maze, List<Pos> loop, Pos pos) {
        if(loop.contains(pos))
            return false;
        int y = pos.y();
        StringBuilder pathChars = new StringBuilder(IntStream.range(0, pos.x())
                .filter(x -> loop.contains(new Pos(y, x)))
                .mapToObj(x -> String.valueOf(maze[y][x]))
                .collect(Collectors.joining()));
        StringUtil.deleteAll(pathChars, '-');
        int checkSum = StringUtil.deleteAll(pathChars, '|');
        checkSum += calculateCheckSum(pathChars);
        return checkSum % 2 == 1;
    }

    private static int calculateCheckSum(StringBuilder pathChars) {
        int result = 0;
        for(int i = 0; i < pathChars.length(); i += 2) {
            char c1 = pathChars.charAt(i), c2 = pathChars.charAt(i+1);
            if(c1 == 'F' && c2 == 'J' || c1 == 'L' && c2 == '7')
                result += 1;
        }
        return result;
    }

    private static Pos findNext(char[][] maze, Pos pos, List<Pos> visited) {
        Pos upPos     = pos.up();
        Pos rightPos  = pos.right();
        Pos downPos   = pos.down();
        Pos leftPos   = pos.left();
        String posName  = String.valueOf(maze[pos.y()][pos.x()]);
        String up       = upPos.y() < 0 ? "" : String.valueOf(maze[upPos.y()][upPos.x()]);
        String right    = rightPos.x() >= maze[0].length ? "" : String.valueOf(maze[rightPos.y()][rightPos.x()]);
        String down     = downPos.y() >= maze.length ? "" : String.valueOf(maze[downPos.y()][downPos.x()]);
        String left     = leftPos.x() < 0 ? "" : String.valueOf(maze[leftPos.y()][leftPos.x()]);
        if(!visited.contains(upPos) && posName.matches("[|LJS]") && up.matches("[|7F]"))
            return upPos;
        if(!visited.contains(rightPos) && posName.matches("[-LFS]") && right.matches("[-J7]"))
            return rightPos;
        if(!visited.contains(downPos) && posName.matches("[|7FS]") && down.matches("[|LJ]"))
            return downPos;
        if(!visited.contains(leftPos) && posName.matches("[-J7S]") && left.matches("[-LF]"))
            return leftPos;
        return Pos.NULL_POS;
    }

    private static Pos findStart(char[][] maze) {
        for(int y = 0; y < maze.length; y++) {
            for(int x = 0; x < maze[0].length; x++) {
                if(maze[y][x] == 'S') {
                    return new Pos(y, x);
                }
            }
        }
        return Pos.NULL_POS;
    }
}
