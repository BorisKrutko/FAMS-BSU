package KRdemo2;

public class MyElement {
    private int value;

    public MyElement(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void accept(MyVisitor visitor) {
        visitor.visit(this.getValue());
    }
}


