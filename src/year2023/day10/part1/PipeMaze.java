package year2023.day10.part1;

import java.util.*;

public class PipeMaze {
    private record Pos(int y, int x) {}

    public static void main(String[] args) throws InterruptedException {
        char[][] maze = util.FileReader
                .readFileAsCharArray("src/year2023/day10/input.txt");
        Pos startPosition = findStart(maze);
        Set<Pos> visited = new HashSet<>();
        List<Pos> currentNodes = findStarts(maze, startPosition);
        visited.add(startPosition);
        int distance;
        for(distance = 2;; distance++) {
            Pos node1 = currentNodes.get(0);
            Pos node2 = currentNodes.get(1);
            currentNodes.clear();
            Pos next1 = findNext(maze, node1, visited);
            Pos next2 = findNext(maze, node2, visited);
            if(next1.equals(next2))
                break;
            visited.add(node1);
            visited.add(node2);
            currentNodes.add(next1);
            currentNodes.add(next2);
        }
        System.out.println(distance);
    }

    private static Pos findNext(char[][] maze, Pos pos, Set<Pos> visited) {
        Pos upPos     = new Pos(pos.y - 1, pos.x);
        Pos rightPos  = new Pos(pos.y, pos.x + 1);
        Pos downPos   = new Pos(pos.y + 1, pos.x);
        Pos leftPos   = new Pos(pos.y, pos.x - 1);
        String posName  = String.valueOf(maze[pos.y][pos.x]);
        String up       = upPos.y < 0 ? "" : String.valueOf(maze[upPos.y][upPos.x]);
        String right    = rightPos.x >= maze[0].length ? "" : String.valueOf(maze[rightPos.y][rightPos.x]);
        String down     = downPos.y >= maze.length ? "" : String.valueOf(maze[downPos.y][downPos.x]);
        String left     = leftPos.x < 0 ? "" : String.valueOf(maze[leftPos.y][leftPos.x]);
        if(!visited.contains(upPos) && posName.matches("[|LJ]") && up.matches("[|7F]"))
            return upPos;
        if(!visited.contains(rightPos) && posName.matches("[-LF]") && right.matches("[-J7]"))
            return rightPos;
        if(!visited.contains(downPos) && posName.matches("[|7F]") && down.matches("[|LJ]"))
            return downPos;
        if(!visited.contains(leftPos) && posName.matches("[-J7]") && left.matches("[-LF]"))
            return leftPos;
        return new Pos(0, 0);
    }

    private static List<Pos> findStarts(char[][] maze, Pos startPos) {
        List<Pos> result = new ArrayList<>();
        Pos upPos     = new Pos(startPos.y - 1, startPos.x);
        Pos rightPos  = new Pos(startPos.y, startPos.x + 1);
        Pos downPos   = new Pos(startPos.y + 1, startPos.x);
        Pos leftPos   = new Pos(startPos.y, startPos.x - 1);
        String up       = String.valueOf(maze[upPos.y][upPos.x]);
        String right    = String.valueOf(maze[rightPos.y][rightPos.x]);
        String down     = String.valueOf(maze[downPos.y][downPos.x]);
        String left     = String.valueOf(maze[leftPos.y][leftPos.x]);
        if(up.matches("[|7F]"))
            result.add(upPos);
        if(right.matches("[-J7]"))
            result.add(rightPos);
        if(down.matches("[|LJ]"))
            result.add(downPos);
        if(left.matches("[-LF]"))
            result.add(leftPos);
        return result;
    }

    private static Pos findStart(char[][] maze) {
        for(int y = 0; y < maze.length; y++) {
            for(int x = 0; x < maze[0].length; x++) {
                if(maze[y][x] == 'S') {
                    return new Pos(y, x);
                }
            }
        }
        return new Pos(0, 0);
    }
}
