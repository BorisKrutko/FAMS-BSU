package LW5;

public class Main {
    public static void main(String[] args) {
        Liner a = new Liner(1, 5);
        System.out.println(a.toString(5));
        System.out.println(a.getSumm(5));
        a.saveToFile("temp", 5);
        new MyFrame();
    }
}
