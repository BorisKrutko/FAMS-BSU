package LW11p;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.Border;

public class MyFrame extends JFrame {
    public MyFrame() {
        setSize(500, 500);
        setLayout(new GridLayout()); 

        JLabel largDisplayJLabel = new JLabel("Нажатая клавиша: ");
        largDisplayJLabel.setFont(new Font("Arial", Font.BOLD, 50)); 
        Border border = BorderFactory.createLineBorder(Color.RED, 2); 
        largDisplayJLabel.setBorder(border); 
        largDisplayJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel logKeyJLabel = new JLabel("Лог нажатий: ");

        LogKey logKey = new LogKey(logKeyJLabel);
        LargeKeyDisplay largeKeyDisplay = new LargeKeyDisplay(largDisplayJLabel);

        Publisher publisher = new Publisher();
        publisher.addObserver(logKey);
        publisher.addObserver(largeKeyDisplay);

        KeyPressHandler keyPressHandler = new KeyPressHandler(publisher);

        this.add(largDisplayJLabel);
        this.add(logKeyJLabel);
        this.addKeyListener(keyPressHandler);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MyFrame::new); // Запуск GUI в потоке событий Swing
    }
}