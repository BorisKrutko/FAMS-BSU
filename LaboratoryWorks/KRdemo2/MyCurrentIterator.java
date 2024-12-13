package KRdemo2;

import java.util.ArrayList;

public class MyCurrentIterator implements MyIterator { 
    private ArrayList<Integer> arrayList;
    private int currentIndex;

    public MyCurrentIterator(MyCollection intSet) {
        this.arrayList = intSet.getArrayList();
        this.currentIndex = 0;
    }

    @Override
    public void first() {
        currentIndex = 0;
    }

    @Override
    public void next() {
        if (hasNext()) {
            currentIndex++;
        }
    }

    private boolean hasNext() {
        return currentIndex < arrayList.size();
    }

    @Override
    public int currentItem() {
        return arrayList.get(currentIndex);
    }

    @Override
    public boolean isDone() {
        return currentIndex >= arrayList.size();
    }

}
