package util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {
    public static int deleteAll(StringBuilder builder, char ch) {
        int result = 0;
        for(int i = 0; i < builder.length(); ) {
            if(builder.charAt(i) == ch) {
                builder.deleteCharAt(i);
                result++;
            }
            else
                i++;
        }
        return result;
    }

    public static List<Integer> stringToIntList(String str) {
        return Arrays.stream(str.split("\\s+"))
                .filter(s -> s.length() != 0)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static int[] stringToIntArray(String str) {
        return Arrays.stream(str.split("\\s+"))
                .filter(s -> s.length() != 0)
                .map(Integer::parseInt)
                .mapToInt(i -> i)
                .toArray();
    }

    public static int[] stringToIntArray(String str, String split) {
        return Arrays.stream(str.split(split))
                .filter(s -> s.length() != 0)
                .map(Integer::parseInt)
                .mapToInt(i -> i)
                .toArray();
    }

    public static List<Long> stringToLongList(String str) {
        return Arrays.stream(str.split("\\s+"))
                .filter(s -> s.length() != 0)
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

    public static long[] stringToLongArray(String str) {
        return Arrays.stream(str.split("\\s+"))
                .filter(s -> s.length() != 0)
                .map(Long::parseLong)
                .mapToLong(l -> l)
                .toArray();
    }
}