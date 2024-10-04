package LW5;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyFrame extends JFrame { 
    public MyFrame() {
        setTitle("My JFrame Example");
        setSize(500, 615);
          
        // Создаем панель и добавляем JComboBox тип прогрессии
        JPanel panel = new JPanel(null); 
        String[] optionTypeSeries = {"Linear", "Exponential"};
        JComboBox<String> comboBox = new JComboBox<>(optionTypeSeries);
        comboBox.setBounds(300, 10, 150, 60);
        panel.add(comboBox);

        // Создаем Введение и добавляем его на панель
        JEditorPane introduction = new JEditorPane();    
        introduction.setContentType("text/html");    
        introduction.setText("<h1>  Series</h1>  Program to work with series.");  
        introduction.setBounds(0, 0, 500, 100);
        panel.add(introduction);

        // Ввод значений
        JLabel labelArgs = new JLabel("  Step:                                           first el:                                   n:");
        labelArgs.setBounds(0, 110, 500, 30);
        panel.add(labelArgs);
        
        JTextField textFieldStep = new JTextField(); // step
        textFieldStep.setBounds(10, 150, 100, 30);
        textFieldStep.setBackground(Color.YELLOW);
        textFieldStep.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        panel.add(textFieldStep);

        JTextField textFieldFirstEl = new JTextField(); // first el
        textFieldFirstEl.setBounds(165, 150, 100, 30);
        textFieldFirstEl.setBackground(Color.YELLOW);
        textFieldFirstEl.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        panel.add(textFieldFirstEl);

        JTextField textFieldN = new JTextField(); // n
        textFieldN.setBounds(310, 150, 50, 30);
        textFieldN.setBackground(Color.YELLOW);
        textFieldN.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        panel.add(textFieldN);

        JTextField textFieldFileName = new JTextField(); // file name
        textFieldFileName.setBounds(10, 490, 100, 30);
        textFieldFileName.setBackground(Color.YELLOW);
        textFieldFileName.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        panel.add(textFieldFileName);

        JLabel labelFileName = new JLabel("  File name");   
        labelFileName.setBounds(120, 490, 265, 30);
        panel.add(labelFileName);

        // Создаем кнопку для печати прогрессии
        JButton printButton = new JButton("print");
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resSeries = "";
                if (comboBox.getSelectedItem().equals("Linear")) {
                    Liner myLiner = new Liner(Double.parseDouble(textFieldFirstEl.getText()), Double.parseDouble(textFieldStep.getText()));
                    resSeries = myLiner.toString(Integer.parseInt(textFieldN.getText()));
                } else {
                    Exponential myExp = new Exponential(Double.parseDouble(textFieldFirstEl.getText()), Double.parseDouble(textFieldStep.getText()));
                    resSeries = myExp.toString(Integer.parseInt(textFieldN.getText()));
                }
                // Создаем JEditorPane и добавляем его на панель
                JEditorPane printSeries = new JEditorPane();    
                printSeries.setContentType("text/html");    
                printSeries.setText("<h1>  Result:</h1>  " + resSeries);  
                printSeries.setBounds(0, 200, 500, 250);
                panel.add(printSeries);
                panel.revalidate();
                panel.repaint();
            }
        });
        printButton.setBounds(400, 150, 70, 30);
        printButton.setBackground(Color.YELLOW);
        panel.add(printButton); 

        // Создаем кнопку для сохранения прогрессии
        JButton saveToFileButton = new JButton("save");
        saveToFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox.getSelectedItem().equals("Linear")) {
                    Liner myLiner = new Liner(Double.parseDouble(textFieldFirstEl.getText()), Double.parseDouble(textFieldStep.getText()));
                    myLiner.saveToFile(myLiner.toString(Integer.parseInt(textFieldN.getText())), Integer.parseInt(textFieldN.getText()));
                } else {
                    Exponential myExp = new Exponential(Double.parseDouble(textFieldFirstEl.getText()), Double.parseDouble(textFieldStep.getText()));
                    myExp.saveToFile(myExp.toString(Integer.parseInt(textFieldN.getText())), Integer.parseInt(textFieldN.getText()));
                }
                // Создаем JEditorPane и добавляем его на панель
                JEditorPane isDone = new JEditorPane();    
                isDone.setContentType("text/html");    
                isDone.setText("<h1>done</h1>");  
                isDone.setBounds(0, 530, 500, 50);
                panel.add(isDone);
                panel.revalidate();
                panel.repaint();
            }
        });
        saveToFileButton.setBounds(400, 490, 70, 30);
        saveToFileButton.setBackground(Color.YELLOW);
        panel.add(saveToFileButton); 


        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

