package KW2.model;

public class IteratorStrategy implements Strategy {
    @Override 
    public int getSize(MyStack myStack) {
        MyIterator it = new MyIterator(myStack);
        int resCount = 0;
        while (!it.isDone()) {
            resCount++;
            it.next();
        } 
        return resCount;
    }
}
