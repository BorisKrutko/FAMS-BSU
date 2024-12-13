package LW12.LW12p2;

import LW12.LW12p2.model.MyHashMap;
import LW12.LW12p2.view.MyView;
import LW12.LW12p2.view.Point2D;

public class Main {
    public static void main(String[] args) {
        MyHashMap<Point2D, String> map = new MyHashMap<>();
        MyView<Point2D, String> view = new MyView<>();
        MapController controller = new MapController<>(map, view);

        controller.put(new Point2D(2, 3), "Boris");
        controller.put(new Point2D(55, 12), "k");
        controller.put(new Point2D(12, 5), "Simon");
        controller.put(new Point2D(7, 7), "Vova");
        controller.put(new Point2D(12, 4), "Vova");

        controller.getView();
    }
}
