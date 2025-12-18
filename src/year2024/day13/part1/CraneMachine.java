package year2024.day13.part1;

import util.io.FileReader;

import java.util.ArrayList;
import java.util.List;

public class CraneMachine {
    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2024/day13/input.txt");
        List<Machine> machines = new ArrayList<>();
        for(int i = 0; i < input.size(); i+=4) {
            String line1 = input.get(i);
            String line2 = input.get(i+1);
            String line3 = input.get(i+2);
            int x1 = Integer.parseInt(line1.substring(12, 14)),
                    y1 = Integer.parseInt(line1.substring(18, 20)),
                    x2 = Integer.parseInt(line2.substring(12, 14)),
                    y2 = Integer.parseInt(line2.substring(18, 20)),
                    X = Integer.parseInt(line3.substring(line3.indexOf('=') + 1, line3.indexOf(','))),
                    Y = Integer.parseInt(line3.substring(line3.lastIndexOf('=') + 1));
            machines.add(new Machine(x1, x2, y1, y2, X, Y));
        }
        int result = machines.parallelStream()
                .mapToInt(Machine::tickets)
                .filter(i -> i != -1)
                .sum();
        System.out.println(result);
    }

    private static record Machine(int x1, int x2, int y1, int y2, int X, int Y) {
        public int tickets() {
            int n2 = (int) Math.round((Y - (double)X * y1 / x1) / (y2 - (double)y1 * x2 / x1));
            int n1 = (int) Math.round((X - (double)n2 * x2) / x1);
            if(x1 * n1 + x2 * n2 == X && y1 * n1 + y2 * n2 == Y)
                return 3 * n1 + n2;
            else
                return -1;
        }
    }
}


