package KRdemo;

import KRdemo.view.MyLabelView;

public class Main {
    public static void main(String[] args) {
        MyCollection myCollection = new MyCollection();
        MyLabelView myLabelView = new MyLabelView(myCollection);

        Controller myController = new Controller(myCollection, myLabelView);
    }
}
