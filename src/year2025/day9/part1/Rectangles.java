package year2025.day9.part1;

import util.io.FileReader;

public class Rectangles {
    public static void main(String[] args) {
        int[][] input = FileReader.readFileAsIntArray("src/year2025/day9/input.txt", ",");
        long result = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < i; j++) {
                long area = calculateArea(input[i], input[j]);
                if (area > result)
                    result = area;
            }
        }
        System.out.println(result);
    }

    private static long calculateArea(int[] p1, int[] p2) {
        long dx = Math.abs(p1[0] - p2[0]) + 1;
        long dy = Math.abs(p1[1] - p2[1]) + 1;
        return  dx * dy;
    }
}
