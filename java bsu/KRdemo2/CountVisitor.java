package KRdemo2;

public class CountVisitor implements MyVisitor {
    private int count = 0;
    
    @Override
    public void visit(Integer item) {
        if (item == 0) this.count++;
    }

    public int getCount() {
        return count;
    }
}
