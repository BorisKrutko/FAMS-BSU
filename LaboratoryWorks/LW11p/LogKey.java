package LW11p;

import javax.swing.JLabel;

public class LogKey implements Observer{
    private JLabel label;

    public LogKey(JLabel label) {
        this.label = label;
    }

    @Override
    public void update(char key) {
        label.setText(this.label.getText() + Character.toString(key));
    }
}
