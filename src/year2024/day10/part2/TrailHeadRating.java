package year2024.day10.part2;

import util.io.FileReader;
import util.Pos;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrailHeadRating {
    public static void main(String[] args) {
        final int[][] map = FileReader.readFileAsIntArray("src/year2024/day10/input.txt");
        List<Pos> startPos = getStartPos(map);
        int result = startPos.stream()
                .mapToInt(pos -> rating(pos, map))
                .sum();
        System.out.println(result);
    }

    private static List<Pos> getStartPos(int[][] map) {
        return IntStream.range(0, map.length)
                .mapToObj(i ->
                    IntStream.range(0, map[0].length)
                            .filter(j -> map[i][j] == 0)
                            .mapToObj(j -> new Pos(i,j))
                            .collect(Collectors.toList()))
                .reduce(new ArrayList<>(), (a, b) -> {a.addAll(b); return a;});
    }

    private static int rating(Pos pos, int[][] map) {
        Set<List<Pos>> paths = Collections.singleton(Collections.singletonList(pos));
        for(int height = 1; height <= 9; height++) {
            Set<List<Pos>> newPaths = new HashSet<>();
            for (List<Pos> path : paths) {
                Pos[] candidates = path.get(height - 1).neighbors();
                for(Pos c : candidates)
                    if (hasValue(c, height, map)) {
                        List<Pos> newPath = new ArrayList<>(path);
                        newPath.add(c);
                        newPaths.add(newPath);
                    }
            }
            paths = newPaths;
        }
        return paths.size();
    }

    private static boolean hasValue(Pos p, int height, int[][] map) {
        return p.y() >= 0 && p.y() < map.length
                && p.x() >= 0 && p.x() < map[0].length
                && map[p.y()][p.x()] == height;
    }
}
