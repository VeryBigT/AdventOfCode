package util;

public record Pos3D(int y, int x, int z) {
    public Pos3D (int... coords) {
        this(coords[0], coords[1], coords[2]);
    }

    public double distance(Pos3D pos) {
        double dx = this.x - pos.x;
        double dy = this.y - pos.y;
        double dz = this.z - pos.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
}
