package year2023.day8.part1;

import java.util.*;

public class PathFinder {
    private static record Node(String left, String right) {}

    public static void main(String[] args) {
        List<String> input = util.FileReader.readFile("src/year2023/day8/input.txt");
        String instructions = input.get(0);
        Map<String,Node> map = new HashMap<>();
        for(int i = 2; i < input.size(); ++i) {
            String line = input.get(i);
            String name = line.substring(0, 3);
            String left = line.substring(7, 10);
            String right = line.substring(12, 15);
            map.put(name, new Node(left, right));
        }
        int steps = 0;
        String currentNode = "AAA";
        do {
            char step = instructions.charAt(steps++ % instructions.length());
            currentNode = step == 'L' ? map.get(currentNode).left : map.get(currentNode).right;
        } while (!currentNode.equals("ZZZ"));
        System.out.println(steps);
    }
}
