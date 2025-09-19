package KRdemo;

public interface MyIterator<T> {
    public  void  first();
    public  void  next();
    public  boolean  isDone();
    public  T  currentItem();
}