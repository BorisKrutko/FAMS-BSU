package LW12.LW12p2.view;

import java.util.ArrayList;

public class MyView<K, V> {
    public MyView() {}

    public void printMyMap(ArrayList<Pair<K, V>> pairs) {
        GraphicVisitor graphicVisitor = new GraphicVisitor();

        for (Pair<K, V> pair : pairs) {
            if (pair.getKey() instanceof Point2D) {
                ((Point2D) pair.getKey()).accept(graphicVisitor);
            } else if (pair.getKey() instanceof Point3D) {
                ((Point3D) pair.getKey()).accept(graphicVisitor);
            } else {
                System.out.println(pair.getKey());
            }
            System.out.println(" : " + pair.getValue() + ", ");
        }
    } 
}
