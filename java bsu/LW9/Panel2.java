package LW9;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Panel2 extends JPanel{
    public Panel2() {
        super(new GridLayout(4, 4));

        MouseAdapter buttonListenerClicked = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JButton source = (JButton) e.getSource();
                source.setText("Clicked!");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                JButton source = (JButton) e.getSource();
                source.setText("");
            }
        };

        MouseAdapter buttonListenerMoved = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                JButton source = (JButton) e.getSource();
                source.setBackground(Color.RED);
            }   

            @Override
            public void mouseExited(MouseEvent e) {
                JButton source = (JButton) e.getSource();
                source.setBackground(Color.BLACK);
            }
        };

        for (int i = 1; i <= 16; i++) {
            JButton button = new JButton();
            button.setBackground(Color.BLACK);
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            button.addMouseMotionListener(buttonListenerMoved); 
            button.addMouseListener(buttonListenerMoved);
            button.addMouseMotionListener(buttonListenerClicked); 
            button.addMouseListener(buttonListenerClicked);
            add(button);
        }
    }
}
