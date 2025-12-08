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

    public static void main(String[] args) {
    }
}
