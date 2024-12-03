package LW11p;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ConcreteSubject extends KeyAdapter {
    private Subject subject;
    
    public ConcreteSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        subject.notifyObservers(key);
    }
}