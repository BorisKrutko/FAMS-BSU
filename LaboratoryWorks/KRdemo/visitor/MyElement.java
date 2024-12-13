package KRdemo.visitor;

public interface MyElement<T>{
    void accept(MyVisitor<T> v);
}
