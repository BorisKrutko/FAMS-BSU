package LW11p;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyPressHandler extends KeyAdapter{
    private Publisher publisher;
    
    public KeyPressHandler(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        publisher.notifyObservers(key);
    }
}
