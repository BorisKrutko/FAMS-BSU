package KRdemo;

import java.util.NoSuchElementException;

public class CurrentIterator implements MyIterator<Integer>{
    private MyCollection stack;
    private int index;

    public CurrentIterator(MyCollection stack) {
        this.stack = stack;
        this.index = 0;
    }

    public boolean hasNext() {
        return index < stack.getArrayList().size();
    }

    @Override
    public void next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Нет больше элементов в итераторе");
        }
        index++;
    }

    @Override
    public void first() {
        this.index = 0;
    }

    @Override
    public Integer currentItem() {
        if (index < 0 || index >= stack.getArrayList().size()) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона");
        }
        return this.stack.getArrayList().get(index);
    }

    @Override
    public boolean isDone() {
        return index >= stack.getArrayList().size();
    }
}
