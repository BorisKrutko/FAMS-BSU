package KRdemo2;

import KRdemo2.MyCollection;

public class Main {
    public static void main(String[] args) {
        MyCollection myCollection = new MyCollection();

        myCollection.add(1);
        myCollection.add(3);
        myCollection.add(5);
        myCollection.add(6);
        System.out.println(myCollection.toString());

    }
}
