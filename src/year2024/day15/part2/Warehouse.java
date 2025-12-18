package year2024.day15.part2;

import util.io.FileReader;
import util.Pos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Warehouse {
    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2024/day15/input.txt");
        List<char[]> room = new ArrayList<>();
        List<Character> commands = new ArrayList<>();
        Pos roboPos = null;
        int i, j;
        String line = input.get(0);
        for(i = 1; !line.isEmpty(); i++) {
            if((j = line.indexOf('@')) > 0)
                roboPos = new Pos(i-1, j * 2);
            room.add(doubleUp(line));
            line = input.get(i);
        }
        for(; i < input.size(); i++) {
            line = input.get(i);
            for(char c : line.toCharArray())
                commands.add(c);
        }
        print(room);
        System.out.println("Commands: " + commands);
        for(char command : commands) {
            //System.out.println("\nCommand: " + command);
            roboPos = update(room, roboPos, command);
            //print(room);
        }
        int result = IntStream.range(0, room.size())
                .map(y -> IntStream.range(0, room.get(0).length)
                            .filter(x -> room.get(y)[x] == 'O')
                            .map(x -> y * 100 + x)
                            .sum())
                .sum();
        System.out.println(result);
    }

    private static char[] doubleUp(String line) {
        char[] result = new char[line.length() * 2];
        for(int i = 0; i < line.length() * 2; i += 2) {
            switch (line.charAt(i / 2)) {
                case 'O' -> {
                    result[i] = '[';
                    result[i + 1] = ']';
                }
                case '@' -> {
                    result[i] = '@';
                    result[i + 1] = '.';
                }
                default -> {
                    result[i] = line.charAt(i / 2);
                    result[i + 1] = line.charAt(i / 2);
                }
            }
        }
        return result;
    }

    private static Pos update(List<char[]> room, Pos pos, char command) {
        return switch (command) {
            case '^' -> move(room, pos, 0, -1);
            case 'v' -> move(room, pos, 0, 1);
            case '<' -> move(room, pos, -1, 0);
            case '>' -> move(room, pos, 1, 0);
            default -> null;
        };
    }

    //TODO
    private static Pos move(List<char[]> room, final Pos pos, int dx, int dy) {
        Pos goal = new Pos(pos.y() + dy, pos.x() + dx);
        int x = pos.x() + dx, y = pos.y() + dy;
        while(room.get(y)[x] != '#') {
            if(room.get(y)[x] == '.') {
                room.get(y)[x] = 'O';
                room.get(pos.y())[pos.x()] = '.';
                room.get(goal.y())[goal.x()] = '@';
                return goal;
            }
            x += dx;
            y += dy;
        }
        return pos;
    }

    private static void print(List<char[]> room) {
        for(char[] arr : room) {
            for(char c : arr)
                System.out.print(c);
            System.out.println();
        }
    }
}
