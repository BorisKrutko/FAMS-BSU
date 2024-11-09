package LW7;

import java.awt.event.*;
import java.util.Random;
import javax.swing.*;


public class MyFramePart2 extends JFrame {
    private static final Random random = new Random();

    public MyFramePart2() {
        setTitle("part2");
        setSize(1000, 1000);
        setLayout(null);

        JLabel quationJLabel = new JLabel("пересдача!?");
        quationJLabel.setBounds(0, 0, 100, 300);

        JButton yesJButton = new JButton("Yes");
        JButton noJButton = new JButton("No");

        yesJButton.setBounds(400, 400, 100, 100);
        noJButton.setBounds(500, 500, 100, 100);

        yesJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                JOptionPane.showMessageDialog(null, "Ты вылетишь из универа))");
            }
        });

        noJButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                int x = random.nextInt(900);
                int y = random.nextInt(900);
                noJButton.setLocation(x, y);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        add(noJButton);
        add(yesJButton);
        add(quationJLabel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
