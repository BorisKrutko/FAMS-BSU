package KW2.model;

public class CountVisitor implements Visitor{
    private Strategy strategy;

    public CountVisitor(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public int visit(MyStack myStack) {
        return this.strategy.getSize(myStack);
    }
}
