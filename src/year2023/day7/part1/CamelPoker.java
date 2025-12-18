package year2023.day7.part1;

import util.io.FileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CamelPoker {
    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2023/day7/input.txt");
        List<Hand> hands = new ArrayList<>();
        for(String line : input) {
            String[] data = line.split(" ");
            hands.add(new Hand(data[0], Integer.parseInt(data[1])));
        }
        Collections.sort(hands);
        int result = 0;
        for(int i = 0; i < hands.size(); ++i) {
            result += (i+1) * hands.get(i).getBet();
        }
        System.out.println(result);
    }
}
