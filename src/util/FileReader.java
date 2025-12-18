package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public abstract class FileReader {
    public static List<String> readFile(String filename) {
        File file = new File(filename);
        ArrayList<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine())
                lines.add(scanner.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static List<List<String>> readFile(String filename, String seperator) {
        File file = new File(filename);
        List<List<String>> result = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine())
                result.add(Arrays.asList((scanner.nextLine().split(seperator))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static char[][] readFileAsCharArray(String filename) {
        List<String> input = readFile(filename);
        char[][] arr = new char[input.size()][];
        for(int i = 0; i < input.size(); ++i)
            arr[i] = input.get(i).toCharArray();
        return arr;
    }

    public static int[][] readFileAsIntArray(String filename) {
        List<String> input = readFile(filename);
        int[][] arr = new int[input.size()][];
        for(int i = 0; i < input.size(); ++i)
            arr[i] = input.get(i).chars()
                    .map(c -> c - '0').toArray();
        return arr;
    }

    public static int[][] readFileAsIntArray(String filename, String seperator) {
        List<List<String>> input = readFile(filename, seperator);
        int[][] result = new int[input.size()][];
        for (int i = 0; i < input.size(); ++i) {
            List<String> line = input.get(i);
            result[i] = line.stream()
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        return result;
    }
}
