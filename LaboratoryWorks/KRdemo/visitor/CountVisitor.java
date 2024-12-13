package KRdemo.visitor;

public class CountVisitor implements MyVisitor<Integer>
{
    private int count = 0;
    @Override
    public void visit(Integer item)
    {
        count++;
    }

    public int getCount()
    {
        return count;
    }
}