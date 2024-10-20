package LW6;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

public class MyFrame extends JFrame {
    File file;
    MyCollection studentCollection = new MyCollection();

    public MyFrame() {
        setTitle("LW6");
        setSize(1000, 500);
        JPanel panelToAddSt = new JPanel();
        JPanel panelToLabels = new JPanel();
        JPanel panelToSort = new JPanel(); 

        JLabel input = new JLabel();
        input.setBackground(Color.YELLOW);
        input.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

        JLabel output = new JLabel();
        output.setBackground(Color.YELLOW);
        output.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

        JTextField coursToSort = new JTextField();
        input.setBackground(Color.YELLOW);
        input.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

        JButton sort = new JButton("Sort");
        sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                output.setText("<html><p style='margin-left: 20px;'>" + MyFrame.this.studentCollection.mapToStringAtRate(Integer.parseInt(coursToSort.getText())).replace("\n", "<br>") + "</html>");
            }
        });

        JTextField studentToAdd = new JTextField();
        input.setBackground(Color.YELLOW);
        input.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

        JButton add = new JButton("add");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                String inputText = studentToAdd.getText();
                if (!inputText.trim().isEmpty()) {
                    String[] args = inputText.split(" ");
                    if (args.length == 4) { // Убедись, что все поля заполнены
                        MyFrame.this.studentCollection.add(new Student(args));
                        input.setText("<html><p style='margin-left: 20px;'>" + MyFrame.this.studentCollection.listToString().replace("\n", "<br>") + "</html>");
                        output.setText("<html><p style='margin-left: 20px;'>" + MyFrame.this.studentCollection.mapToString().replace("\n", "<br>") + "</html>");
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
                    MyFrame.this.file = fileChooser.getSelectedFile();
                    MyFrame.this.studentCollection = new MyCollection(MyFrame.this.file);
                    input.setText("<html><p style='margin-left: 20px;'>" + MyFrame.this.studentCollection.listToString().replace("\n", "<br>") + "</html>");
                }
            }
        });

        panelToAddSt.add(studentToAdd);
        panelToAddSt.add(add);
        panelToAddSt.setLayout(new GridLayout(1, 2));

        panelToLabels.add(input);
        panelToLabels.add(output);
        panelToLabels.setLayout(new GridLayout(1, 2));

        panelToSort.add(sort);
        panelToSort.add(coursToSort);
        panelToSort.setLayout(new GridLayout(1, 2));

        add(panelToSort, BorderLayout.SOUTH);
        add(panelToAddSt, BorderLayout.NORTH);
        add(openFile, BorderLayout.WEST);
        add(panelToLabels, BorderLayout.CENTER); 

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Инициализация коллекции
        studentCollection = new MyCollection();
    }
}

