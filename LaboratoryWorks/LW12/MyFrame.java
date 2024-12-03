package LW12;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

public class MyFrame extends JFrame {
    private HashMap<String, Integer> map;

    public MyFrame() {
        map = new HashMap<>();
        setSize(1000, 1000);
        setLayout(new GridLayout(5, 1));

        JLabel inputJLabel = new JLabel();
        JButton putButton = new JButton("put");
        JTextField fieldToPut = new JTextField("key value");
        JTextField fieldToGet = new JTextField("key to find");
        JButton getButton = new JButton("get");
        JButton clearButton = new JButton("clear");

        putButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                String[] keyValue = fieldToPut.getText().split(" ");
                if (keyValue.length == 2) {
                    map.put(keyValue[0], Integer.parseInt(keyValue[1]));
                    inputJLabel.setText("Stored: " + keyValue[0] + " = " + keyValue[1]);
                } else {
                    inputJLabel.setText("Invalid input. Use 'key value' format.");
                }
            }
        });


        getButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                String key = fieldToGet.getText();
                Integer value = map.get(key);
                if (value != null) {
                    inputJLabel.setText("Found: " + key + " = " + value);
                } else {
                    inputJLabel.setText("Key not found.");
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                map.clear();
                inputJLabel.setText("Map cleared.");
            }
        });

        add(fieldToPut);
        add(putButton);
        add(fieldToGet);
        add(getButton);
        add(clearButton);
        add(inputJLabel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MyFrame();
    }
}
