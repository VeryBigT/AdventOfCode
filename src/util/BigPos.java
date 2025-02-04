package util;

public record BigPos(long y, long x) {
    public static final Pos NULL_POS = new Pos(-1, -1);

    public BigPos up() {
        return new BigPos(y-1, x);
    }

    public BigPos right() {
        return new BigPos(y, x+1);
    }

    public BigPos down() {
        return new BigPos(y+1, x);
    }

    public BigPos left() {
        return new BigPos(y, x-1);
    }
}