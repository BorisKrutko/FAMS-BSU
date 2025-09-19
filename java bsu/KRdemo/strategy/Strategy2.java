package KRdemo.strategy;

import java.util.ArrayList;
import java.util.List;

public class Strategy2 implements MyStrategy{
    public List<Integer> sort(List<Integer> unsorted) {
        List<Integer> unsortedList = new ArrayList<>(unsorted);
        unsortedList.sort(null);
        return unsortedList;
    }
}
