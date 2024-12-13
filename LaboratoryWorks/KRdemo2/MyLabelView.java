package KRdemo2;

import java.awt.Label;

public class MyLabelView extends Label implements MyView {
    private MyCollection myCollection;

    public MyLabelView(MyCollection myCollection) {
        this.myCollection = myCollection;
    }

    @Override
    public void update() {
        setText(myCollection.toString());
    }
}
