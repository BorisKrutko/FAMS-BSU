package KRdemo.strategy;

import java.util.List;

public class Strategy1 implements MyStrategy{
    public List<Integer> sort(List<Integer> unsorted) {
        return unsorted.stream().sorted().toList();
    }
}
