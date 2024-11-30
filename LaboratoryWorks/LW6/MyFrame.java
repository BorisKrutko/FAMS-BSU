package LW6;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

public class MyFrame extends JFrame {
    private File file;
    private MyCollection studentCollection;

    public MyFrame() {
        studentCollection = new MyCollection();
        setTitle("LW6");
        setSize(1000, 500);
        setLayout(new BorderLayout());

        // Panels
        JPanel panelToAddSt = new JPanel(new GridLayout(1, 2));
        JPanel panelToLabels = new JPanel(new GridLayout(1, 2));
        JPanel panelToSort = new JPanel(new GridLayout(1, 2));

        // Labels
        JLabel inputLabel = new JLabel();
        inputLabel.setBackground(Color.YELLOW);
        inputLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        inputLabel.setOpaque(true); // Make background visible

        JLabel outputLabel = new JLabel();
        outputLabel.setBackground(Color.YELLOW);
        outputLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        outputLabel.setOpaque(true); // Make background visible

        // Text Fields
        JTextField courseToSort = new JTextField("input course to sort: ");
        JTextField studentToAdd = new JTextField("id name course group");

        // Sort Button
        JButton sortButton = new JButton("Sort");
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                try {
                    int course = Integer.parseInt(courseToSort.getText());
                    String sortedStudents = studentCollection.mapToStringAtRate(course);
                    outputLabel.setText("<html><p style='margin-left: 20px;'>" + sortedStudents.replace("\n", "<br>") + "</html>");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid course number.");
                }
            }
        });

        // Add Button
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                String inputText = studentToAdd.getText();
                if (!inputText.trim().isEmpty()) {
                    String[] args = inputText.split(" ");
                    if (args.length == 4) {
                        studentCollection.add(new Student(args));
                        inputLabel.setText("<html><p style='margin-left: 20px;'>" + studentCollection.listToString().replace("\n", "<br>") + "</html>");
                        outputLabel.setText("<html><p style='margin-left: 20px;'>" + studentCollection.mapToString().replace("\n", "<br>") + "</html>");
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter all fields: idNumber, surname, groupNumber, courseNumber");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Input cannot be empty");
                }
            }
        });

        // Open File Button
        JButton openFileButton = new JButton("Open");
        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    studentCollection = new MyCollection(file);
                    inputLabel.setText("<html><p style='margin-left: 20px;'>" + studentCollection.listToString().replace("\n", "<br>") + "</html>");
                }
            }
        });

        // Adding components to panels
        panelToAddSt.add(studentToAdd);
        panelToAddSt.add(addButton);
        panelToLabels.add(inputLabel);
        panelToLabels.add(outputLabel);
        panelToSort.add(sortButton);
        panelToSort.add(courseToSort);

        // Adding panels to frame
        add(panelToSort, BorderLayout.SOUTH);
        add(panelToAddSt, BorderLayout.NORTH);
        add(openFileButton, BorderLayout.WEST);
        add(panelToLabels, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}