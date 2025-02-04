package year2024.day12.part2;

import util.FileReader;
import util.ListMap;
import util.Pos;
import util.Range;

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
                .mapToInt(region -> region.size() * numberOfSides(region))
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

    private static int numberOfSides(List<Pos> region) {
        ListMap<Integer, Range> horizontals = new ListMap<>();
        ListMap<Integer, Range> verticals = new ListMap<>();
        region.forEach(pos -> {
            Pos neighbor = pos.up();
            if(!region.contains(neighbor))
                horizontals.add(3 * pos.y() - 1, new Range(neighbor.x(), neighbor.x()+1));
            neighbor = pos.down();
            if(!region.contains(neighbor))
                horizontals.add(3 * pos.y() + 1, new Range(neighbor.x(), neighbor.x()+1));
            neighbor = pos.left();
            if(!region.contains(neighbor))
                verticals.add(3 * pos.x() - 1, new Range(neighbor.y(), neighbor.y()+1));
            neighbor = pos.right();
            if(!region.contains(neighbor))
                verticals.add(3 * pos.x() + 1, new Range(neighbor.y(), neighbor.y()+1));
        });
        int result = 0;
        for(Collection<Range> coll : horizontals.values())
            result += Range.combine(coll).size();
        for(Collection<Range> coll : verticals.values())
            result += Range.combine(coll).size();
        return result;
    }
}
