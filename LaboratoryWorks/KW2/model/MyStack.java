package KW2.model;

import java.util.ArrayList;
import java.util.zip.DataFormatException;

public class MyStack {
    private ArrayList<Integer> arrayList;
    private int size;

    public ArrayList<Integer> getArrayList() { return this.arrayList; }

    public MyStack() {
        this.arrayList = new ArrayList<>();
        this.size = 0;
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

    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }

    public void push (int element) {
        this.arrayList.add(element);
        size++;
    }
}
