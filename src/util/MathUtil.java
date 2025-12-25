package util;

public abstract class MathUtil {
    public static int mod(int a, int b) {
        int result = a % b;
        return result >= 0 ? result : result + b;
    }

    public static long mod(long a, long b) {
        long result = a % b;
        return result >= 0 ? result : result + b;
    }

    public static int min(int... ints) {
        if (ints.length == 0)
            throw new IllegalArgumentException("Input array is empty");
        int result = ints[0];
        for (int i = 1; i < ints.length; i++)
            if (ints[i] < result)
                result = ints[i];
        return result;
    }

    public static long min(long... ints) {
        if (ints.length == 0)
            throw new IllegalArgumentException("Input array is empty");
        long result = ints[0];
        for (int i = 1; i < ints.length; i++)
            if (ints[i] < result)
                result = ints[i];
        return result;
    }

    public static int max(int... ints) {
        if (ints.length == 0)
            throw new IllegalArgumentException("Input array is empty");
        int result = ints[0];
        for (int i = 1; i < ints.length; i++)
            if (ints[i] > result)
                result = ints[i];
        return result;
    }

    public static long max(long... ints) {
        if (ints.length == 0)
            throw new IllegalArgumentException("Input array is empty");
        long result = ints[0];
        for (int i = 1; i < ints.length; i++)
            if (ints[i] > result)
                result = ints[i];
        return result;
    }

    public static void main(String[] args) {
    }
}
