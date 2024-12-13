package KRdemo.visitor;

public class StringVisitor implements MyVisitor<Integer>{
    StringBuilder sb = new StringBuilder();
    @Override
    public void visit(Integer value) {
        sb.append(value);
        sb.append(" ");
    }


    public String getResult() {
        return sb.toString();
    }   
}
