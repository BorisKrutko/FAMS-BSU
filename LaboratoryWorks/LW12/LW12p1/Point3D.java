package LW12.LW12p1;

public class Point3D {
    private final int x;
    private final int y;
    private final int z;

    public Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getZ() { return z; }
}