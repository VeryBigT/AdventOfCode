package year2023.day3.part2;

import util.io.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScematicAnalyser {

    private record P(int x, int y){}


    public static void main(String[] args) {
        char[][] data = FileReader.readFileAsCharArray("src/year2023/day3/input.txt");
        int result = 0;
        for(int y = 0; y < data.length; ++y) {
            for(int x = 0; x < data[0].length; ++x) {
                if (data[y][x] == '*') {
                    result += gearRatio(data, x, y);
                }
            }
        }
        System.out.println(result);
    }

    private static int gearRatio(char[][] data, int x, int y) {
        if(numOfNumbers(data, x, y) != 2)
            return 0;
        int result = 1;
        List<P> neighbours = getNeighbours(data, x, y);
        List<P> visited = new ArrayList<>();
        for(P neighbour : neighbours) {
            if(visited.contains(neighbour) || !isDigit(data, neighbour))
                continue;
            int start = neighbour.x, end = neighbour.x;
            while(isDigit(data, start, neighbour.y)) {
                visited.add(new P(start--, neighbour.y));
                if(start == -1)
                    break;
            }
            start++;
            while(isDigit(data, end, neighbour.y)) {
                visited.add(new P(end++, neighbour.y));
                if(end == data[0].length)
                    break;
            }
            end--;
            int n = getNumber(data, start, end, neighbour.y);
            result *= n;
        }
        return result;
    }

    private static boolean isDigit(char[][] data, int x, int y) {
        return Character.isDigit(data[y][x]);
    }

    private static boolean isDigit(char[][] data, P p) {
        return Character.isDigit(data[p.y][p.x]);
    }

    private static int getNumber(char[][] data, int start, int end, int y) {
        return Integer.parseInt(String.valueOf(Arrays.copyOfRange(data[y], start, end+1)));
    }

    private static boolean[] neighbourNumbers(char[][] data, int x, int y) {
        return new boolean[] {
                Character.isDigit(data[y-1][x-1]),
                Character.isDigit(data[y-1][x]),
                Character.isDigit(data[y-1][x+1]),
                Character.isDigit(data[y][x-1]),
                Character.isDigit(data[y][x+1]),
                Character.isDigit(data[y+1][x-1]),
                Character.isDigit(data[y+1][x]),
                Character.isDigit(data[y+1][x+1])
        };
    }

    private static int numOfNumbers(char[][] data, int x, int y) {
        boolean[] arr = neighbourNumbers(data, x, y);
        int result = 0;
        if(arr[0] && !arr[1] && arr[2])
            result += 2;
        else if(arr[0] || arr[1] || arr[2])
            result++;
        if(arr[3])
            result++;
        if(arr[4])
            result++;
        if(arr[5] && !arr[6] && arr[7])
            result += 2;
        else if(arr[5] || arr[6] || arr[7])
            result++;
        return result;
    }

    private static List<P> getNeighbours(char[][] data, int x, int y) {
        ArrayList<P> result = new ArrayList<>();
        result.add(new P(x-1,y-1));
        result.add(new P(x,y-1));
        result.add(new P(x+1,y-1));
        result.add(new P(x-1, y));
        result.add(new P(x+1, y));
        result.add(new P(x-1,y+1));
        result.add(new P(x,y+1));
        result.add(new P(x+1,y+1));
        return result;
    }
}
