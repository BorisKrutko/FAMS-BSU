package LW5;

import java.io.FileWriter;
import java.io.IOException;


public abstract class Series {
    protected double initial_el;
    protected double step;

    public Series(double initial_el, double step) {
        this.initial_el = initial_el;
        this.step = step;
    }

    public abstract double elementCalculation(int j);

    public double getSumm(int n) {
        double resSumm = 0;
        for (int i = 1; i <= n; i++) {
            resSumm += this.elementCalculation(i);
        }
        return resSumm;
    }

    public String toString(int n) {
        String resStr = new String();
        for (int i = 1; i <= n; i++) {
            resStr += String.valueOf(this.elementCalculation(i)) + " ";
        }
        return resStr;
    }   

    public void saveToFile (String fileName, int n) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(this.toString(n));
            System.out.println("Successfully appended to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
