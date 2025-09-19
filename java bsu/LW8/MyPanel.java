package LW8;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import javax.swing.JPanel;

public class MyPanel extends JPanel implements MouseListener, MouseMotionListener{
    //private Stack<List<MyPoint>> lines = new Stack<>();
    private List<MyPoint> tempLine = new ArrayList<>();
    private Color tempColor;
    private static int brushSize;
    private BufferedImage myImage;

    public class MyPoint extends Point{
        private Color color;
        private static int brushSize = 5;

        public MyPoint(int x, int y, Color color) {
            super(x, y);
            this.color = color;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public int getBrushSize() {
            return brushSize;
        }
    }

    public MyPanel(int WIDTH, int HEIGHT) {
        tempColor = Color.BLACK;
        myImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = myImage.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, myImage.getWidth(), myImage.getHeight());
        g2d.dispose();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    private void drawLines(Graphics2D g2d) {
        for (int i = 1; i < tempLine.size(); i++) {
            MyPoint point = tempLine.get(i - 1);
            MyPoint nextPoint = tempLine.get(i);
            g2d.setColor(point.getColor());
            g2d.setStroke(new BasicStroke(point.getBrushSize(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.drawLine(point.x, point.y, nextPoint.x, nextPoint.y);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(myImage, 0, 0, null);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        tempLine.add(new MyPoint(e.getX(), e.getY(), tempColor));

        Graphics2D g2d = myImage.createGraphics();
        drawLines(g2d);
        g2d.dispose();
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        tempLine = new ArrayList<>();
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void setColor(Color color) {
        tempColor = color;
    }

    public Color getColor() {
        return tempColor;
    }

    public int getBrushSize() {
        return brushSize;
    }

    public BufferedImage saveImage() {
        BufferedImage image = new BufferedImage(myImage.getWidth(), myImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.drawImage(myImage, 0, 0, null);
        g2d.dispose();
        return image;
    }

    public void loadImage(BufferedImage loadedImage){
        tempLine = new ArrayList<>();
        brushSize = 5;
        tempColor = Color.BLACK;
        Graphics2D g2d = myImage.createGraphics();
        g2d.drawImage(loadedImage, 0, 0, null);
        g2d.dispose();
        repaint();
    }
}
