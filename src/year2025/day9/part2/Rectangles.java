package year2025.day9.part2;

import util.io.FileReader;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Rectangles {
    public static void main(String[] args) {
        int[][] input = FileReader.readFileAsIntArray("src/year2025/day9/input.txt", ",");
        List<HorizontalLine> horizontalLines = new ArrayList<>();
        List<VerticalLine> verticalLines = new ArrayList<>();
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            int j = (i + 1) % input.length;
            int p1x = input[i][0], p1y = input[i][1];
            int p2x = input[j][0], p2y = input[j][1];
            points.add(new Point(p1x, p1y));
            if (p1x == p2x)
                verticalLines.add(new VerticalLine(p1x, p1y, p2y));
            else //if (p1y == p2y)
                horizontalLines.add(new HorizontalLine(p1y, p1x, p2x));
        }
        verticalLines.sort(Comparator.comparingInt(VerticalLine::x));
        horizontalLines.sort(Comparator.comparingInt(HorizontalLine::y));
        long result = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < i; j++) {
                Point pi = points.get(i);
                Point pj = points.get(j);
                if(!rectangleIsInside(pi, pj, horizontalLines, verticalLines))
                    continue;
                long area = calculateArea(pi, pj);
                if (area > result)
                    result = area;
            }
        }
        System.out.println(result); // soll 24 sein
    }

    private static boolean rectangleIsInside(Point p1, Point p2, List<HorizontalLine> horizontalLines, List<VerticalLine> verticalLines) {
        int minX = Math.min(p1.x(), p2.x());
        int minY = Math.min(p1.y(), p2.y());
        int maxX = Math.max(p1.x(), p2.x());
        int maxY = Math.max(p1.y(), p2.y());
        for (int x = minX; x <= maxX; x++) {

        }
        for (int y = minY; y <= maxY; y++) {

        }
        return true;
    }

    private static long calculateArea(Point p1, Point p2) {
        long dx = Math.abs(p1.x() - p2.x()) + 1;
        long dy = Math.abs(p1.y() - p2.y()) + 1;
        return dx * dy;
    }
}

// records
record HorizontalLine(int y, int x1, int x2){
    HorizontalLine(int y, int x1, int x2){
        this.y = y;
        this.x1 = Math.min(x1, x2);
        this.x2 = Math.max(x1, x2);
    }

    public boolean contains(int x, int y) {
        return y == this.y && x >= x1 && x <= x2;
    }
}

record VerticalLine(int x, int y1, int y2){
    VerticalLine(int x, int y1, int y2){
        this.x = x;
        this.y1 = Math.min(y1, y2);
        this.y2 = Math.max(y1, y2);
    }

    public boolean contains(int x, int y) {
        return x == this.x && y >= this.y1 && y <= this.y2;
    }
}

record Point(int x, int y){}
