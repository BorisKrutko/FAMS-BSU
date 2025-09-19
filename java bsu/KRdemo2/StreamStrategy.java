package KRdemo2;

import java.util.ArrayList;

public class StreamStrategy implements MyStrategy {
    @Override
    public int getCount(MyCollection collection) {
        ArrayList<Integer> arrayList = collection.getArrayList();
        return (int) arrayList.stream().filter(i -> i == 1).count();
    }    
}
