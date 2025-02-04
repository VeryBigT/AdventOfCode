package year2024.day8.part2;

import util.FileReader;
import util.ListMap;
import util.Pos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                .map(list -> antinodesPos(l, w, list))
                .reduce(new HashSet<>(), (a,b) -> {a.addAll(b); return a;})
                .size();
        System.out.println(num);
    }

    private static Set<Pos> antinodesPos(int l, int w, List<Pos> list) {
        Set<Pos> antinodes = new HashSet<>();
        for(int i = 0; i < list.size()-1; i++) {
            for(int j = i+1; j < list.size(); j++) {
                Pos pos1 = list.get(i), pos2 = list.get(j);
                int dx = pos2.x() - pos1.x(), dy = pos2.y() - pos1.y();
                antinodes.add(pos1);
                antinodes.add(pos2);
                for(int n = 1;; n++) {
                    Pos newPos = new Pos(pos1.y() - n * dy, pos1.x() - n * dx);
                    if(!inBounds(newPos, l, w))
                        break;
                    antinodes.add(newPos);
                }
                for(int n = 1;; n++) {
                    Pos newPos = new Pos(pos2.y() + n * dy, pos2.x() + n * dx);
                    if(!inBounds(newPos, l, w))
                        break;
                    antinodes.add(newPos);
                }
            }
        }
        return antinodes;
    }

    private static boolean inBounds(Pos pos, int l, int w) {
        return pos.y() >= 0 && pos.y() < l && pos.x() >= 0 && pos.x() < w;
    }
}
