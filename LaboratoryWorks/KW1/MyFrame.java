package KW1;

import javax.swing.*;
import java.awt.event.*;
import java.util.function.Predicate;
import java.util.Comparator;
import java.awt.*;


public class MyFrame extends JFrame{
    private Present present;

    public void JFrame() {
        setTitle("LW6");
        setSize(1000, 500);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 4));

        JLabel input = new JLabel();
        input.setBackground(Color.YELLOW);
        input.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

        JLabel output = new JLabel();
        output.setBackground(Color.YELLOW);
        output.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

        JButton openFile = new JButton("open");
        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    MyFrame.this.present = new Present(fileChooser.getSelectedFile());
                    input.setText("<html><p style='margin-left: 20px;'>" + MyFrame.this.present.arrayListToString().replace("\n", "<br>") + "</html>");
                }
            }
        });

        String[] compToSort = {"name", "waight", "sugar percrntage"};
        JComboBox<String> comboBox = new JComboBox<>(compToSort);
        
        JButton sort = new JButton("sort");
        sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Comparator<Candy> comparator;
                switch (comboBox.getActionCommand()) {
                    case "name":
                        comparator = (c1, c2) -> c1.getName().compareTo(c2.getName());
                        MyFrame.this.present.sort(comparator);
                        break;
                    case "waight":
                        comparator = (c1, c2) -> Double.compare(c1.getWaight(), c2.getWaight());
                        MyFrame.this.present.sort(comparator);
                        break;
                    case "sugar percrntage":
                        comparator = (c1, c2) -> Double.compare(c1.getWaight(), c2.getWaight());
                        MyFrame.this.present.sort(comparator);
                        break;
                }
                output.setText("<html><p style='margin-left: 20px;'>" + MyFrame.this.present.setToString().replace("\n", "<br>") + "</html>");
            }
        });

        panel.add(comboBox);
        panel.add(sort);
        panel.add(input);
        panel.add(output);
        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
