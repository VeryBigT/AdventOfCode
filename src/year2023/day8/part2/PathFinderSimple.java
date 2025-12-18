package year2023.day8.part2;

import util.io.FileReader;

import java.util.*;
import java.util.stream.IntStream;

public class PathFinderSimple {
    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2023/day8/input.txt");
        Boolean[] instructions = input.get(0).chars().mapToObj(c -> c == 'L').toArray(Boolean[]::new);
        input = input.subList(2, input.size());
        input.sort((n1, n2) -> {
            char c1 = n1.charAt(2), c2 = n2.charAt(2);
            if (c1 != c2)
                return c1 - c2;
            c1 = n1.charAt(1); c2 = n2.charAt(1);
            if (c1 != c2)
                return c1 - c2;
            c1 = n1.charAt(0); c2 = n2.charAt(0);
            return c1 - c2;
        });
        System.out.println(input);

        int[] currentNodes = new int[]{0,1,2,3,4,5}; //because it's sorted
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
        System.out.println("setup done!");
        System.out.println(Arrays.toString(instructions));
        System.out.println(Arrays.toString(nodes));
        System.out.println(Arrays.toString(lefts));
        System.out.println(Arrays.toString(rights));
        long steps = 0;
        do {
            boolean left = instructions[(int) (steps++ % instructions.length)];
            for(int i = 0; i < 6; i++) {
                currentNodes[i] = left ? lefts[currentNodes[i]] : rights[currentNodes[i]];
            }
        } while (IntStream.of(currentNodes).sum() != 4191); //sum of the last 4 indices
        System.out.println(steps);
    }

    private static int find(String[] nodes, String node) {
        for(int i = 0; i < nodes.length; i++) {
            if(nodes[i].equals(node))
                return i;
        }
        return -1;
    }
}
