package util;

import java.util.Arrays;
import java.util.NoSuchElementException;

public abstract class MathUtil {
    public static int mod(int a, int b) {
        int result = a % b;
        return result >= 0 ? result : result + b;
    }

    public static long mod(long a, long b) {
        long result = a % b;
        return result >= 0 ? result : result + b;
    }

    public static int min(int... values) {
        if (values.length == 0)
            throw new IllegalArgumentException("Input array is empty");
        return Arrays.stream(values).min().orElse(0);
    }

    public static long min(long... values) {
        if (values.length == 0)
            throw new IllegalArgumentException("Input array is empty");
        return Arrays.stream(values).min().orElse(0);
    }

    public static double min(double... values) {
        if (values.length == 0)
            throw new IllegalArgumentException("Input array is empty");
        return Arrays.stream(values).min().orElse(0);
    }

    public static int max(int... values) {
        if (values.length == 0)
            throw new IllegalArgumentException("Input array is empty");
        return Arrays.stream(values).max().orElse(0);
    }

    public static long max(long... values) {
        if (values.length == 0)
            throw new IllegalArgumentException("Input array is empty");
        return Arrays.stream(values).max().orElse(0);
    }

    public static double max(double... values) throws NoSuchElementException {
        if (values.length == 0)
            throw new IllegalArgumentException("Input array is empty");
        return Arrays.stream(values).max().orElse(0);
    }

    public static void main(String[] args) {
        System.out.println(min(1, 2, 3));
        System.out.println(max(1L, 2L, 3L));
        System.out.println(max(1.1, 2.2, 3.2));
    }
}
