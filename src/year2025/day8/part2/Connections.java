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
        List<Pair<Pair<Integer,Integer>,Double>> connections = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < i; j++) {
                double distance = positions[i].distance(positions[j]);
                connections.add(new Pair<>(new Pair<>(i, j), distance));
            }
        }
        connections.sort(Comparator.comparingDouble(Pair::right));
        List<Set<Integer>> circuits = new ArrayList<>();
        int i = 0, j = 0;
        for (var connection : connections) {
            i = connection.left().left();
            j = connection.left().right();
            circuits.add(setOf(i, j));
            reduce(circuits);
            if (circuits.size() == 1 && circuits.get(0).size() == size)
                break;
        }
        long result = (long) positions[i].x() * (long) positions[j].x();
        System.out.println(result);
    }
}
