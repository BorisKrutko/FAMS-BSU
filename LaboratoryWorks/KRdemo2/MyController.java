package KRdemo2;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

public class MyController extends JFrame {
    private MyCollection myCollection;
    private MyLabelView myView;
    private JButton addButton;
    private JButton saveButton;
    private JButton countButton;
    private JTextField inputField;
    private JComboBox<String> strategyComboBox;

    public MyController(MyCollection myCollection, MyLabelView myView) {
        this.myCollection = myCollection;
        this.myView = myView;
        setLayout(new GridLayout());
        add(myView);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        inputField = new JTextField("element:");
        addButton = new JButton("Add");
        panel.add(inputField);
        panel.add(addButton);
        add(panel);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(inputField.getText());
                myCollection.add(value);
                myView.update();
            }
        });

        saveButton = new JButton("Save");
        add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    myCollection.save("output.txt");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        strategyComboBox = new JComboBox<>(new String[]{"StreamStrategy", "OtherStrategy"});
        add(strategyComboBox);

        countButton = new JButton("Count");
        add(countButton);
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedStrategy = (String) strategyComboBox.getSelectedItem();
                if ("StreamStrategy".equals(selectedStrategy)) {
                    myCollection.setStrategy(new StreamStrategy());
                } else {
                    myCollection.setStrategy(new SimpleStrategy());
                }
                int count = myCollection.getCount();
                JOptionPane.showMessageDialog(null, "Count: " + count);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        MyCollection collection = new MyCollection(0);
        MyLabelView view = new MyLabelView(collection);
        new MyController(collection, view);
    }
}
