package year2024.day12.part1;

import util.io.FileReader;
import util.Pos;

import java.util.*;

public class FencePrice {
    public static void main(String[] args) {
        char[][] field = FileReader.readFileAsCharArray("src/year2024/day12/input.txt");
        boolean[][] visited = new boolean[field.length][];
        for(int i = 0; i < visited.length; i++) {
            visited[i] = new boolean[field[0].length];
        }
        List<List<Pos>> regions = new ArrayList<>();
        for(int i = 0; i < field.length; i++) {
            for(int j = 0; j < field[0].length; j++) {
                if(visited[i][j])
                    continue;
                List<Pos> region = new ArrayList<>();
                fillRegion(region, new Pos(i, j), field, visited);
                regions.add(region);
            }
        }
        int result = regions.parallelStream()
                .mapToInt(region -> region.size() * perimeter(region, field))
                .sum();
        System.out.println(result);
    }

    private static void fillRegion(List<Pos> region, Pos pos, char[][] field, boolean[][] visited) {
        if(!isInBounds(pos, field) || visited[pos.y()][pos.x()])
            return;
        visited[pos.y()][pos.x()] = true;
        region.add(pos);
        for(Pos neighbour : pos.neighbors())
            if(isInBounds(neighbour, field) && field[neighbour.y()][neighbour.x()] == field[pos.y()][pos.x()])
                fillRegion(region, neighbour, field, visited);
    }

    private static boolean isInBounds(Pos pos, char[][] arr) {
        return pos.y() >= 0 && pos.y() < arr.length && pos.x() >= 0 && pos.x() < arr[0].length;
    }

    private static int perimeter(List<Pos> region, char[][] field) {
        return region.stream()
                .mapToInt(pos -> (int) Arrays.stream(pos.neighbors())
                        .filter(neighbour -> !isInBounds(neighbour, field) || !region.contains(neighbour))
                        .count())
                .sum();
    }
}
