package year2024.day14.part2;

import util.io.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestroomRobos {
    static final int width = 101, height = 103;

    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2024/day14/input.txt");
        int time  = 0;
        List<Robo> robos = new ArrayList<>();
        for(String line : input) {
            String[] tmp = line.split(" ");
            robos.add(new Robo(
                    Integer.parseInt(tmp[0].substring(tmp[0].indexOf('=') + 1, tmp[0].indexOf(','))),
                    Integer.parseInt(tmp[0].substring(tmp[0].indexOf(',') + 1)),
                    Integer.parseInt(tmp[1].substring(tmp[1].indexOf('=') + 1, tmp[1].indexOf(','))),
                    Integer.parseInt(tmp[1].substring(tmp[1].indexOf(',') + 1))
            ));
        }
        char[][] room = new char[103][];
        for(int i = 0; i < height; i++)
            room[i] = new char[width];
        do {
            time++;
            for (char[] line : room)
                Arrays.fill(line, '.');
            for (Robo r : robos) {
                r.move();
                room[r.y][r.x] = 'X';
            }
        } while (!hasChain(room));
        System.out.println("time = " + time);
        print(room);
    }

    private static boolean hasChain(char[][] room) {
        for(char[] line : room)
            if(new String(line).contains("XXXXXXXXXXX"))
                return true;
        return false;
    }

    private static void print(char[][] room) {
        for(int y = 0; y < height; y++)
                System.out.println(room[y]);
    }

    static class Robo {
        int x, y, dx, dy;
        Robo(int x, int y, int dx, int dy) {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
        }
        void move() {
            x = (x + dx) % width;
            x = x < 0 ? width + x : x;
            y = (y + dy) % height;
            y = y < 0 ? height + y : y;
        }
    }
}
