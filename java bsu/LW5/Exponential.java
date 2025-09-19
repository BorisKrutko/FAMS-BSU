package LW5;

public class Exponential extends Series {
    public Exponential(double initial_el, double step) {
        super(initial_el, step);
    }

    @Override
    public double elementCalculation(int j) {
        return initial_el + Math.pow(step, j - 1);
    }
}
