package year2023.day8.part2;

import util.io.FileReader;

import java.util.List;
import java.util.stream.IntStream;

public class PathFinderLCM {

    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2023/day8/input.txt");
        Boolean[] instructions = input.get(0).chars()
                .mapToObj(c -> c == 'L')
                .toArray(Boolean[]::new);
        input = input.subList(2, input.size());
        input.sort((n1, n2) -> {
            char c1 = n1.charAt(2), c2 = n2.charAt(2);
            if (c1 != c2) return c1 - c2;
            c1 = n1.charAt(1); c2 = n2.charAt(1);
            if (c1 != c2) return c1 - c2;
            c1 = n1.charAt(0); c2 = n2.charAt(0);
            return c1 - c2;
        });

        String[] nodes = new String[input.size()];
        int[] lefts = new int[input.size()];
        int[] rights = new int[input.size()];
        for (int i = 0; i < input.size(); i++) {
            String name = input.get(i).substring(0, 3);
            nodes[i] = name;
        }
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            lefts[i] = find(nodes, line.substring(7, 10));
            rights[i] = find(nodes, line.substring(12, 15));
        }

        int[] distances = IntStream.range(0, 6)
                .parallel()
                .map(i -> {
                    int currentNode = i;
                    for(int distance = 0;; distance++) {
                        currentNode = instructions[distance % instructions.length]
                                ? lefts[currentNode] : rights[currentNode];
                        if(currentNode >= 696) {
                            return distance + 1;
                        }
                    }
                })
                //.peek(System.out::println)
                .toArray();

        long result = IntStream.of(distances)
                .mapToLong(x -> x)
                .reduce(1, PathFinderLCM::kgV);
        System.out.println(result);
    }

    private static long ggT(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static long kgV(long a, long b) {
        return a * b / ggT(a, b);
    }

    private static int find(String[] nodes, String node) {
        for(int i = 0; i < nodes.length; i++) {
            if(nodes[i].equals(node))
                return i;
        }
        return -1;
    }
}
