package util.io;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class FileWriter {
    public static void writeFile(String filename, String content) {
        try (java.io.FileWriter writer = new java.io.FileWriter(filename)) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeFile(String filename, List<String> content) {
        writeFile(filename, String.join("\n", content));
    }

    public static void writeFile(String filename, List<List<String>> content, String separator) {
        writeFile(filename, content.stream()
                .map(list -> String.join(separator, list))
                .collect(Collectors.toList()));
    }

    public static void writeFile(String filename, char[][] content) {
        writeFile(filename, Arrays.stream(content)
                .map(String::valueOf)
                .collect(Collectors.joining("\n")));
    }

    public static void writeFile(String filename, int[][] content) {
        writeFile(filename, Arrays.stream(content)
                .map(arr -> Arrays.stream(arr)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining()))
                .collect(Collectors.joining("\n")));
    }
}
