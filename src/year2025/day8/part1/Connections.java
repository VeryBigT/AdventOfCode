package year2025.day8.part1;

import util.io.FileReader;
import util.collection.Pair;
import util.Pos3D;

import java.util.*;

import static util.collection.Collections.*;

public class Connections {
    public static void main(String[] args) {
        int[][] input = FileReader.readFileAsIntArray("src/year2025/day8/input.txt", ",");
        Pos3D[] positions = Arrays.stream(input)
                .map(Pos3D::new)
                .toArray(Pos3D[]::new);
        List<Pair<Pair<Integer,Integer>,Double>> list = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < i; j++) {
                double distance = positions[i].distance(positions[j]);
                list.add(new Pair<>(new Pair<>(i, j), distance));
            }
        }
        List<Set<Integer>> circuits = new ArrayList<>();
        list.stream()
                .sorted(Comparator.comparingDouble(Pair::right))
                .limit(1000)
                .map(Pair::left)
                .forEach(pair -> {
                    Set<Integer> set = setOf(pair.left(), pair.right());
                    for(Set<Integer> circuit: circuits) {
                        if(hasIntersection(set, circuit)) {
                            circuit.addAll(set);
                            return;
                        }
                    }
                    circuits.add(set);
                });
        reduce(circuits);
        int result = circuits.stream()
                .sorted((set1, set2) -> set2.size() - set1.size())
                .limit(3)
                .mapToInt(Set::size)
                .reduce(1, (a, b) -> a * b);
        System.out.println(result); // 153328
    }
}
