package LW5;

public class Liner extends Series{
    public Liner(double initial_el, double step) {
        super(initial_el, step);
    }

    @Override
    public double elementCalculation(int j) {
        return initial_el + step * (j - 1);
    }

}
