package KW2.model;

import java.util.NoSuchElementException;

public class MyIterator {
    private MyStack stack;
    private int index;

    public MyIterator(MyStack stack) {
        this.stack = stack;
        this.index = 0;
    }

    public boolean hasNext() {
        return index < stack.getArrayList().size();
    }

    public void next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Нет больше элементов в итераторе");
        }
        index++;
    }

    public void first() {
        this.index = 0;
    }

    public int currentItem() {
        if (index < 0 || index >= stack.getArrayList().size()) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона");
        }
        return this.stack.getArrayList().get(index);
    }

    public boolean isDone() {
        return index >= stack.getArrayList().size();
    }
}