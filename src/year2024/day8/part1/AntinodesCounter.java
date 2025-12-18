package year2024.day8.part1;

import util.io.FileReader;
import util.collection.ListMap;
import util.Pos;

import java.util.*;
import java.util.stream.Collectors;

public class AntinodesCounter {
    public static void main(String[] args) {
        char[][] input = FileReader.readFileAsCharArray("src/year2024/day8/input.txt");
        ListMap<Character, Pos> map = new ListMap<>();
        final int l = input.length, w = input[0].length;
        for(int i = 0; i < l; i++) {
            for(int j = 0; j < w; j++) {
                char c = input[i][j];
                if(c != '.')
                    map.add(c, new Pos(i, j));
            }
        }
        int num = map.values().stream()
                .map(list -> antinodesPos(list))
                .map(list -> list.stream()
                        .filter(pos -> inBounds(pos, l, w))
                        .collect(Collectors.toSet()))
                .reduce(new HashSet<Pos>(), (a,b) -> {a.addAll(b); return a;})
                .size();
        System.out.println(num);
    }

    private static List<Pos> antinodesPos(List<Pos> list) {
        List<Pos> antinodes = new ArrayList<>();
        for(int i = 0; i < list.size()-1; i++) {
            for(int j = i+1; j < list.size(); j++) {
                Pos pos1 = list.get(i), pos2 = list.get(j);
                int dx = pos2.x() - pos1.x(), dy = pos2.y() - pos1.y();
                antinodes.add(new Pos(pos1.y() - dy, pos1.x() - dx));
                antinodes.add(new Pos(pos2.y() + dy, pos2.x() + dx));
            }
        }
        return antinodes;
    }

    private static boolean inBounds(Pos pos, int l, int w) {
        return pos.y() >= 0 && pos.y() < l && pos.x() >= 0 && pos.x() < w;
    }
}
