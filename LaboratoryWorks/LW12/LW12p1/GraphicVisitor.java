package LW12;

public class GraphicVisitor implements Visitor{
    @Override
    public void visit(Point2D point2D) {
        System.out.printf("Visiting Point2D: (%d, %d)%n", point2D.getX(), point2D.getY());
    }

    @Override
    public void visit(Point3D point3D) {
        System.out.printf("Visiting Point3D: (%d, %d, %d)%n", point3D.getX(), point3D.getY(), point3D.getZ());
    }
}
