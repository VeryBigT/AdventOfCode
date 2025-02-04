package year2023.day3.part1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScematicAnalyser {

    private record P(int x, int y){}

    public static void main(String[] args) {
        int result = 0;
        List<String> input = util.FileReader.readFile("src/year2023/day3/input.txt");
        char[][] data = new char[input.size()][];
        for(int i = 0; i < input.size(); ++i) {
            data[i] = input.get(i).toCharArray();
        }
        int start = 0, end = 0, x, y;
        boolean onNumber = false;
        for(y = 0; y < data.length; ++y) {
            for(x = 0; x < data[0].length; ++x) {
                if(Character.isDigit(data[y][x])) {
                    if(!onNumber) {
                        start = x;
                        onNumber = true;
                    }
                    end = x;
                }
                else /*no digit*/ {
                    if(onNumber) {
                        onNumber = false;
                        if(checkNeighbours(data, start, end, y))
                           result += getNumber(data, start, end, y);
                    }
                }
            }
            if(onNumber) {
                onNumber = false;
                if(checkNeighbours(data, start, end, y))
                    result += getNumber(data, start, end, y);
            }
        }
        System.out.println(result);
    }

    private static int getNumber(char[][] data, int start, int end, int y) {
        return Integer.parseInt(String.valueOf(Arrays.copyOfRange(data[y], start, end+1)));
    }

    private static boolean checkNeighbours(char[][] data, int start, int end, int y) {
        List<P> neighbours = getNeighbours(data, start, end, y);
        for(P neighbour: neighbours) {
            char c = data[neighbour.y][neighbour.x];
            if(!Character.isDigit(c) && c != '.')
                return true;
        }
        return false;
    }

    private static List<P> getNeighbours(char[][] data, int start, int end, int y) {
        ArrayList<P> result = new ArrayList<>();
        boolean firstRow = y == 0, lastRow = y == data.length-1;
        boolean firstColumn = start == 0, lastColumn = end == data[y].length-1;
        if(!firstColumn)
            result.add(new P(start-1, y));
        if(!lastColumn)
            result.add(new P(end+1, y));
        for(int x = start - (firstColumn ? 0 : 1); x <= end + (lastColumn ? 0 : 1); ++x) {
            if(!firstRow)
                result.add(new P(x,y-1));
            if(!lastRow)
                result.add(new P(x,y+1));
        }
        return result;
    }
}
