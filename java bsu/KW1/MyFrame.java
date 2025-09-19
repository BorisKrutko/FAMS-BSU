package KW1;

import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;
import javax.swing.*;


public class MyFrame extends JFrame{
    private Present present;

    public MyFrame(Present present) {
        this.present = present;
        setTitle("LW6");
        setSize(1000, 500);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel input = new JLabel("input");
        input.setBackground(Color.YELLOW);
        input.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

        JLabel output = new JLabel("output");
        output.setBackground(Color.YELLOW);
        output.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

        JTextField studentToAdd = new JTextField("student to add");
        input.setBackground(Color.YELLOW);
        input.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

        JButton add = new JButton("add");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                String inputText = studentToAdd.getText();
                if (!inputText.trim().isEmpty()) {
                    String[] args = inputText.split(" ");
                    if (args.length == 9) { // Убедись, что все поля заполнены
                        MyFrame.this.present.add(args);
                        input.setText("<html><p style='margin-left: 20px;'>" + MyFrame.this.present.arrayListToString().replace("\n", "<br>") + "</html>");
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter all fields: idNumber, surname, groupNumber, courseNumber");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Input cannot be empty");
                }
            }
        });


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
                switch ((String) comboBox.getSelectedItem()) {
                    case "name":
                        comparator = (c1, c2) -> c1.getName().compareTo(c2.getName());
                        MyFrame.this.present.sort(comparator);
                        break;
                    case "waight":
                        comparator = (c1, c2) -> Double.compare(c1.getWaight(), c2.getWaight());
                        MyFrame.this.present.sort(comparator);
                        break;
                    case "sugar percrntage":
                        comparator = (c1, c2) -> Double.compare(c1.getSugarPercrntage(), c2.getSugarPercrntage());
                        MyFrame.this.present.sort(comparator);
                        break;
                }
                output.setText("<html><p style='margin-left: 20px;'>" + MyFrame.this.present.ListToString().replace("\n", "<br>") + "</html>");
            }
        });

        panel.add(comboBox);
        panel.add(sort);
        panel.add(input);
        panel.add(output);
        panel.add(add);
        panel.add(studentToAdd);
        panel.add(openFile);
        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
