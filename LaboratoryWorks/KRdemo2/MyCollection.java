package KRdemo2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class MyCollection {
    private ArrayList<Integer> arrayList;
    private int min;
    private MyStrategy strategy;

    public MyCollection(int min) {
        this.min = min;
        this.arrayList = new ArrayList<>();
        for (int i = 0; i < min; i++) { 
            arrayList.add(0); 
        }
        this.strategy = new StreamStrategy();
    }

    public void setStrategy(MyStrategy strategy) {
        this.strategy = strategy;
    }

    public int getCount() {
        return this.strategy.getCount(this);
    }

    public ArrayList<Integer> getArrayList() {
        return arrayList;
    }

    public void add(int el) {
        if (el < this.min) {
            int zerosToAdd = this.min - el;
            for (int i = 0; i < zerosToAdd; i++) {
                this.arrayList.add(0, 0);
            }
            this.min = el;
        }

        int index = min + el;
        if (index < this.arrayList.size()) {
            for (int i = arrayList.size(); i <= index; i++) { 
                arrayList.add(0); 
            }
        } else {
            this.arrayList.add(index, 1);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Integer i : arrayList) {
            sb.append(i.toString()).append(" ");
        }
        return sb.toString();
    }

    public void save(String fName) throws IOException {
        try (FileWriter writer = new FileWriter(fName)) {
            writer.append(this.toString());
            writer.append("/n");
            for (int i = 0; i < this.arrayList.size(); i++) {
                if (i != 0) {
                    writer.append((min + i) + " ");
                }
            }
            
        } 
    }

    public int accept(CountVisitor v) {
        for (Integer el : this.arrayList) {
            MyElement tempElement = new MyElement(el);
            tempElement.accept(v);
        }
        return v.getCount();
    }
}
