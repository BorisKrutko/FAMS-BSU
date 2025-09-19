package KW2.model;

public class SimpleStrategy implements Strategy {
    @Override
    public int getSize(MyStack myStack) {
        return myStack.getArrayList().size();
    }
}
