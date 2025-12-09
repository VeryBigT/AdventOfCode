package util;

public record Pos(int y, int x) {
    public static final Pos NULL_POS = new Pos(-1, -1);

    public Pos up() {
        return new Pos(y-1, x);
    }

    public Pos right() {
        return new Pos(y, x+1);
    }

    public Pos down() {
        return new Pos(y+1, x);
    }

    public Pos left() {
        return new Pos(y, x-1);
    }

    public Pos[] neighbors() {
        return new Pos[]{up(), down(), left(), right()};
    }

    public Pos[] allNeighbors() {
        return new Pos[]{up(), down(), left(), right(),
                new Pos(y-1, x-1), new Pos(y+1, x+1),
                new Pos(y-1, x+1), new Pos(y+1, x-1)};
    }
}
