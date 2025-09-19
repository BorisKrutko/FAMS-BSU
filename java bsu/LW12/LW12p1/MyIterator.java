package LW12.LW12p1;

import java.util.ArrayList;

public class MyIterator<K, V> {
    private MyHashMap<K, V> map;
    private ArrayList<Pair<K, V>> pairList;
    private int index;

    public MyIterator(MyHashMap<K, V> map) {
        this.map = map;
        this.pairList = map.getArrayList();
        this.index = 0;
        getNextRealEl();
    }

    private void getNextRealEl() {
        this.pairList = this.map.getArrayList();
        while (index < this.map.getCapacity() && this.pairList.get(this.index) == null) {
            this.index++;
        }
    }

    public void first() {
        this.index = 0;
        getNextRealEl();
    }

    public void next() {
        this.index++;
        getNextRealEl();
    }

    public boolean isDone() {
        return index == this.map.getCapacity();
    }

    public Pair<K, V> currentItem() {
        return this.map.getArrayList().get(index);
    }
}
