package LW12.LW12p1;

public class Main {
    public static void main(String[] args) {
        MyHashMap<Point2D, String> map = new MyHashMap<>();

        map.put(new Point2D(2, 3), "Boris");
        map.put(new Point2D(55, 12), "k");
        map.put(new Point2D(12, 5), "Simon");
        map.put(new Point2D(7, 7), "Vova");
        map.put(new Point2D(12, 4), "Vova");

        GraphicVisitor graphicVisitor = new GraphicVisitor();

        MyIterator myIterator = new MyIterator<>(map);

        while (!myIterator.isDone()) {
            ((Point2D) myIterator.currentItem().getKey()).accept(graphicVisitor);
            myIterator.next();
        }
    }
}
