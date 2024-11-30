package LW11p;

import javax.swing.JLabel;

public class LargeKeyDisplay implements Observer {
    private JLabel label;

    public LargeKeyDisplay(JLabel label) {
        this.label = label;
    }

    @Override
    public void update(char key) {
        label.setText(Character.toString(key));
    }
}
