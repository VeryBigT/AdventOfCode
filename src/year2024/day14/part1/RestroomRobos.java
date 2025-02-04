package year2024.day14.part1;

import util.FileReader;

import java.util.ArrayList;
import java.util.List;

public class RestroomRobos {
    public static void main(String[] args) {
        List<String> input = FileReader.readFile("src/year2024/day14/input.txt");
        int width = 101, height = 103;
        int time  = 100;
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
        int[] quads = new int[4];
        int x, y;
        for(Robo robo : robos) {
            x = (robo.x + time * robo.dx) % width;
            x = x < 0 ? width + x : x;
            y = (robo.y + time * robo.dy) % height;
            y = y < 0 ? height + y : y;
            if(x < width / 2) {
                if(y < height / 2)
                    quads[0]++;
                else if(y > height / 2)
                    quads[1]++;
            }
            else if(x > width / 2) {
                if(y < height / 2)
                    quads[2]++;
                else if(y > height / 2)
                    quads[3]++;
            }
        }
        int result = quads[0] * quads[1] * quads[2] * quads[3];
        System.out.println(result);
    }

    private record Robo(int x, int y, int dx, int dy) {}
}
