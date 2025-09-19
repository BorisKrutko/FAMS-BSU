package LW8;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MyFrame extends JFrame {
    public MyFrame() {
        setTitle("LW8");
        setSize(500, 500);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        MyPanel drawingPanel = new MyPanel(2000, 2000);
        drawingPanel.setPreferredSize(new Dimension(2000, 2000)); 
        JScrollPane scrollPane = new JScrollPane(drawingPanel);

        JPanel panel1 = new JPanel();
        JButton setRed = new JButton("red");
        JButton setGreen = new JButton("green");
        JButton setBlack = new JButton("black");
        setRed.setBackground(Color.RED);
        setBlack.setBackground(Color.BLACK);
        setGreen.setBackground(Color.GREEN);
        panel1.add(setBlack);
        panel1.add(setGreen);
        panel1.add(setRed);

        JButton saveButton = new JButton("save");
        saveButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);

            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                try {
                    BufferedImage image = drawingPanel.saveImage();
                    ImageIO.write(image, "png", fileToSave);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel1.add(saveButton);
        
        JButton loadButton = new JButton("load");
        loadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
            int userSelection = fileChooser.showOpenDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToLoad = fileChooser.getSelectedFile();
                try {
                    BufferedImage bufferedImage = ImageIO.read(fileToLoad);
                    drawingPanel.loadImage(bufferedImage);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel1.add(loadButton);

        setBlack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.setColor(Color.BLACK);
            }
        });

        setRed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.setColor(Color.RED);
            }
        });

        setGreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.setColor(Color.GREEN);
            }
        });

        setLayout(new BorderLayout());
        add(panel1, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MyFrame();
    }
}



