package LW12.LW12p2;

import LW12.LW12p2.model.MyHashMap;
import LW12.LW12p2.view.MyView;
import LW12.LW12p2.view.Pair;
import java.util.ArrayList;

public class MapController<K, V> {
    private MyHashMap<K, V> hashMap;
    private MyView<K, V> view;

    public MapController(MyHashMap<K, V> hashMap, MyView<K, V> view) {
        this.hashMap = hashMap;
        this.view = view;
    }

    public void put(K key, V val) {
        this.hashMap.put(key, val);
    }

    public V get(K key) {
        return (V) this.hashMap.get(key);
    }

    public void clear() {
        this.hashMap.clear();
    }

    public int size() {
        return this.hashMap.size();
    }
    
    public void getView() {
        ArrayList<Pair<K, V>> pairs = new ArrayList<>();
        for (Pair<K, V> pair : this.hashMap.getArrayList()) {
            if (pair != null) {
                pairs.add(pair);
            }
        }
        this.view.printMyMap(pairs);
    }
}
