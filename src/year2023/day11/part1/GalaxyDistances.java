package year2023.day11.part1;

import util.FileReader;
import util.Pos;

import java.util.ArrayList;
import java.util.List;

public class GalaxyDistances {
    public static void main(String[] args) {
        char[][] universe = FileReader.readFileAsCharArray("src/year2023/day11/input.txt");
        List<Integer> emptyRows = getEmptyRows(universe);
        List<Integer> emptyColumns = getEmptyColumns(universe);
        List<Pos> galaxies = new ArrayList<>();
        int px, py;
        for (int y = 0; y < universe.length; y++) {
            for (int x = 0; x < universe[0].length; x++) {
                if(universe[y][x] == '#') {
                    py = y;
                    px = x;
                    for(int row : emptyRows) {
                        if(row > y)
                            break;
                        py++;
                    }
                    for(int column : emptyColumns) {
                        if(column > x)
                            break;
                        px++;
                    }
                    galaxies.add(new Pos(py, px));
                }
            }
        }
        int result = 0;
        for(int a = 0; a < galaxies.size(); a++) {
            Pos galaxyA = galaxies.get(a);
            for(int b = a; b < galaxies.size(); b++) {
                Pos galaxyB = galaxies.get(b);
                result += distance(galaxyA, galaxyB);
            }
        }
        System.out.println(result);
    }

    private static List<Integer> getEmptyColumns(char[][] universe) {
        List<Integer> result = new ArrayList<>();
        for (int x = 0; x < universe[0].length; x++) {
            boolean empty = true;
            for (int y = 0; y < universe.length; y++) {
                if (universe[y][x] != '.') {
                    empty = false;
                    break;
                }
            }
            if (empty)
                result.add(x);
        }
        return result;
    }

    private static List<Integer> getEmptyRows(char[][] universe) {
        List<Integer> result = new ArrayList<>();
        for (int y = 0; y < universe.length; y++) {
            boolean empty = true;
            for (int x = 0; x < universe[0].length; x++) {
                if (universe[y][x] != '.') {
                    empty = false;
                    break;
                }
            }
            if (empty)
                result.add(y);
        }
        return result;
    }

    private static int distance(Pos galaxyA, Pos galaxyB) {
        return Math.abs(galaxyA.x() - galaxyB.x()) + Math.abs(galaxyA.y() - galaxyB.y());
    }
}
