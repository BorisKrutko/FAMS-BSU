package LW11p;

import javax.swing.JLabel;

public class ConcreteObserverB implements Observer {
    private JLabel label;

    public ConcreteObserverB(JLabel label) {
        this.label = label;
    }

    @Override
    public void update(char key) {
        label.setText(Character.toString(key));
    }
}