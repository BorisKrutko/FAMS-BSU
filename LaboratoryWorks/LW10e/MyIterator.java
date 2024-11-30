package LW10e;

import java.util.ArrayList;

public class MyIterator<K, V> {
    private MyHashMap<K, V> map;
    private ArrayList<Pair<K, V>> listPairs;
    private K currentKey;

    public MyIterator(MyHashMap<K, V> map){
        this.map = map;
        this.listPairs = map.getArrayList();
        this.currentKey = 
    }

    private Pair<K, V> getNextNotEmptyPair(K key) {

    } 

    public void next() {
        
    }

    public void first() {

    }

    public Pair<K, V> currentItem() {

    }

    public boolean isDone() {

    } 
}
