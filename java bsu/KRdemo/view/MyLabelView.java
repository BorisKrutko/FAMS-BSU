package KRdemo.view;

import javax.swing.JLabel;

import KRdemo.MyCollection;
import KRdemo.visitor.StringVisitor;

public class MyLabelView extends JLabel implements MyView {
    private MyCollection myCollection;
    private StringVisitor stringVisitor  = new StringVisitor();

    public MyLabelView(MyCollection myCollection) {
        this.myCollection = myCollection;
    }
    @Override
    public void update() {
        myCollection.accept(stringVisitor);
        this.setText(stringVisitor.getResult());
        this.update();
    }
}
