package LW11p;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    } 

    public void removeObserver(Observer observer) {
        observers.removeIf(el -> el.equals(observer));
    } 

    public void notifyObservers(char key) {
        observers.forEach(el -> el.update(key));
    }
}
