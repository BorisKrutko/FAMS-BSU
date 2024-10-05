package LW5;

public class main {
    public static void main(String[] args) {
        Liner a = new Liner(1, 5);
        System.out.println(a.toString(5));
        System.out.println(a.getSumm(5));
        a.saveToFile("curva", 5);

        new MyFrame();
    }
}
