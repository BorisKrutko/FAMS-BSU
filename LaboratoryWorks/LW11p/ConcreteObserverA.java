package LW11p;

import javax.swing.JLabel;

public class ConcreteObserverA implements Observer {
    private JLabel label;

    public ConcreteObserverA(JLabel label) {
        this.label = label;
    }

    @Override
    public void update(char key) {
        label.setText(this.label.getText() + Character.toString(key));
    }
}