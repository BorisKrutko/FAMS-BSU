package LW12.LW12p1;

public class Point2D {
    private final int x;
    private final int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}