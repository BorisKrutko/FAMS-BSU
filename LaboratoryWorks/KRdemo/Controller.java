package KRdemo;

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

import KRdemo.view.MyLabelView;

public class Controller extends JFrame{
    private MyCollection model;
    private MyLabelView view;

    public Controller(MyCollection model, MyLabelView view) {
        this.model = model;
        this.view = view;
        setSize(1000, 1000);

        JTextField textFieldPush = new JTextField("el to push");

        JButton popButton = new JButton("Pop");
        popButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.pop();
                view.update();
            }
        });

        JButton pushButton = new JButton("Push");
        pushButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.push(Integer.parseInt(textFieldPush.getText()));
                view.update();
            }
        });
        
        setLayout(new GridLayout());
        add(this.view);
        add(textFieldPush);
        add(pushButton);
        add(popButton);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


}
