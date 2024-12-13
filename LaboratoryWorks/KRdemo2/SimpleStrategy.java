package KRdemo2;

import java.util.ArrayList;

public class SimpleStrategy implements MyStrategy{
    @Override
    public int getCount(MyCollection collection) {
        int size = 0;
        MyCurrentIterator iterator = new MyCurrentIterator(collection);
        while (!iterator.isDone()) {
            if (iterator.currentItem() == 1) size++;
            iterator.next();
        }
        return size;
    } 
}
