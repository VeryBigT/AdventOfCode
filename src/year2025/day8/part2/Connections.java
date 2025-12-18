package year2025.day8.part2;

import util.io.FileReader;
import util.collection.Pair;
import util.Pos3D;

import java.util.*;

import static util.collection.Collections.*;

public class Connections {
    public static void main(String[] args) {
        int[][] input = FileReader.readFileAsIntArray("src/year2025/day8/input.txt", ",");
        int size = input.length;
        Pos3D[] positions = Arrays.stream(input)
                .map(Pos3D::new)
                .toArray(Pos3D[]::new);
        List<Pair<Pair<Integer,Integer>,Double>> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < i; j++) {
                double distance = positions[i].distance(positions[j]);
                list.add(new Pair<>(new Pair<>(i, j), distance));
            }
        }
        list.sort(Comparator.comparingDouble(Pair::right));
        List<Set<Integer>> circuits = new ArrayList<>();
        int n = 0;
        long result;
        do {
            var connection = list.get(n++);
            int i = connection.left().left();
            int j = connection.left().right();
            result = (long) positions[i].x() * (long) positions[j].x();
            circuits.add(setOf(i, j));
            reduce(circuits);
        } while (!(circuits.size() == 1 && circuits.get(0).size() == size));
        System.out.println(result);
    }
}
