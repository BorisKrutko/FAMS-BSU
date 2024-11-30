package LW10e;

import java.math.BigInteger;
import java.util.ArrayList;

class MyHashMap<K, V> {
    private ArrayList<Pair<K, V>> arrayList;
    private int capacity;
    private int size;

    public ArrayList<Pair<K, V>> getArrayList() { return this.arrayList; }

    public MyHashMap() {
        this.capacity = 13;
        this.arrayList = new ArrayList<>(17);
        for (int i = 0; i < capacity; i++) {
            arrayList.add(null); // Initialize with nulls
        }
        this.size = 0;
    }

    private int firstHashF(K object) {
        return Math.abs(object.hashCode()) % capacity;
    }
    
    private int secondHashF(int ind) {
        return 5 + ind % 5; // 5 is random simple numb (find in the book) 
    }
    
    private int findNextNearestSimpleNum(int capacity) {
        BigInteger bigInt = BigInteger.valueOf(capacity);
        while (!bigInt.isProbablePrime(100)) {
            bigInt = bigInt.add(BigInteger.ONE);
        }
        return bigInt.intValue();
    }
    
    private void resizeMyHashMap() {
        int temp = this.capacity;
        this.capacity = findNextNearestSimpleNum(2 * this.capacity);
        ArrayList<Pair<K, V>> newList = new ArrayList<>(this.capacity);
        for (int i = 0; i < this.capacity; i++) {
            newList.add(null); 
        }
    
        for (int i = 0; i < temp; i++) {
            if (arrayList.get(i) != null) {
                Pair<K, V> pair = arrayList.get(i);
                int indToPut = firstHashF(pair.getKey());
                int step = secondHashF(indToPut);
                while (newList.get(indToPut) != null) {
                    indToPut = (indToPut + step) % this.capacity;
                }
                newList.set(indToPut, pair);
            }
        }
        this.arrayList = newList;
    }

    public void put(K key, V val) {
        if (size + 1 > 0.75 * this.capacity) resizeMyHashMap();
        int indToPut = firstHashF(key);
        int step = secondHashF(indToPut);
        while (arrayList.get(indToPut) != null) {
            indToPut = (indToPut + step) % capacity;
        }
        arrayList.set(indToPut, new Pair<>(key, val));
        size++;
    }

    public void putAll(ArrayList<Pair<K, V>> pairs) {
        for (Pair<K, V> pair : pairs) {
            this.put(pair.getKey(), pair.getValue());
        }
    }

    public V get(K key) {
        int indToPut = firstHashF(key);
        int step = secondHashF(indToPut);

        while (arrayList.get(indToPut) != null) {
            if (arrayList.get(indToPut).getKey().equals(key)) return arrayList.get(indToPut).getValue();
            indToPut = (indToPut + step) % capacity;
        }
        return null;
    }

    public int size() { return this.size; }

    public void clear() {
        this.arrayList = new ArrayList<>(17);
        this.size = 0;
        this.capacity = 17;
        for (int i = 0; i < capacity; i++) {
            arrayList.add(null); // Reinitialize
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Pair<K, V> pair : this.arrayList) {
            if (pair != null) {
                res.append("<").append(pair.getKey()).append(", ").append(pair.getValue()).append("> ");
            }
        }
        return res.toString();
    }

    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyHashMap<K, V> other = (MyHashMap<K, V>) o;
        if (this.size != other.size) return false;

        for (int i = 0; i < this.capacity; i++) {
            if (!this.arrayList.get(i).equals(other.arrayList.get(i))) {
                return false;
            }
        }
        return true;
    }
}
