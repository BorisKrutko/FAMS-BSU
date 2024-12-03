package LW11p1;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer) {
        observers.add(observer);
    } 

    public void detach(Observer observer) {
        observers.removeIf(el -> el.equals(observer));
    } 

    public void notifyObservers(char key) {
        observers.forEach(el -> el.update(key));
    }
}