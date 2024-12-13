package KRdemo;
import KRdemo.strategy.MyStrategy;
import KRdemo.strategy.Strategy1;
import KRdemo.visitor.MyElement;
import KRdemo.visitor.MyVisitor;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class MyCollection implements MyIterable<Integer>, MyElement<Integer>{
    private List<Integer> arrayList;
    private int size;
    private MyStrategy myStrategy;

    public List<Integer> getArrayList() { return this.arrayList; }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (Integer i : arrayList) {
            sb.append(i.toString());
            sb.append(" ");
        }
        return sb.toString();
    }

    public MyCollection() {
        this.arrayList = new ArrayList<>();
        this.size = 0;
        this.myStrategy = new Strategy1();
    }

    public void setStrategy(MyStrategy myStrategy) {
        this.myStrategy = myStrategy;
    }

    public List<Integer> sort() {
        return this.myStrategy.sort(this.arrayList);
    }

    public int pop() {
        int lastEl = this.arrayList.getLast();
        this.arrayList.removeLast();
        try {
            if (this.size == 0) throw new DataFormatException();
        } catch (DataFormatException e) {}
        this.size--;
        return lastEl;
    }

    public void push (int element) {
        this.arrayList.add(element);
        size++;
    }

    @Override
    public int accept(MyVisitor<Integer> v) {
        CurrentIterator it = new CurrentIterator(this);
        for(it.first(); !it.isDone(); it.next()) {
            v.visit(it.currentItem());
        }
        return v.getCount();
    }

    @Override
    public MyIterator<Integer> cteateIterator() {
        return 
    }

}
