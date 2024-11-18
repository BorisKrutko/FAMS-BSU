package LW9;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

class MyFrame extends JFrame {
    public MyFrame() {
        setTitle("LW9");
        setSize(1000, 1000);
        
        JTabbedPane tabbedPane = new JTabbedPane();
        Panel1 panel1 = new Panel1();
        Panel2 panel2 = new Panel2();
        Panel3 panel3 = new Panel3();
        tabbedPane.add(panel1);
        tabbedPane.add(panel2);
        tabbedPane.add(panel3);
        add(tabbedPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    } 

    public static void main(String[] args) {
        new MyFrame();
    }
}

