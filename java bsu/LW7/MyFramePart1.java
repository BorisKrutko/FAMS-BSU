package LW7;

import java.awt.event.*;
import javax.swing.*;


public class MyFramePart1 extends JFrame implements MouseListener, MouseMotionListener {
    boolean isCtrlPressed = false;
    JButton button = new JButton("потаскай");
    JLabel XYMouse = new JLabel("X_Y");

    public MyFramePart1() {
        setTitle("LW7-1");
        setSize(300, 300); // Увеличил размер окна для удобства
        setLayout(null);
        addMouseListener(this);
        addMouseMotionListener(this);

        add(XYMouse);
        XYMouse.setBounds(0, 0, 100, 100);
        add(button);
        button.setBounds(100, 100, 190, 50);

        button.setFocusable(true);
        button.requestFocusInWindow();

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if ((e.getModifiersEx() & MouseEvent.CTRL_DOWN_MASK) != 0) {
                    isCtrlPressed = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isCtrlPressed = false;
            }
        });

        button.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isCtrlPressed) {
                    java.awt.Point point = SwingUtilities.convertPoint(button, e.getX(), e.getY(), button.getParent());
                    button.setLocation(point.x - button.getWidth() / 2, point.y - button.getHeight() / 2);
                }
            }
        });

        button.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if (!button.getText().equals("")) {
                        button.setText(button.getText().substring(0, button.getText().length() - 1));
                    }
                } else if (e.getKeyChar() == ' ' || Character.isLetterOrDigit(e.getKeyChar())) {
                    button.setText(button.getText() + e.getKeyChar());
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        button.setLocation(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        XYMouse.setText(e.getX() + " : " + e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

}