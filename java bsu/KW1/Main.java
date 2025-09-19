package KW1;

public class Main {
    public static void main(String[] args) {
        int a = 12;
        System.err.println("a: " + a);
        //String[] args {"L", "0.22", "ChupaChupa", "R", "Lolli", "40", "0", "num1", "1"};
        //LolliPop lolliPop = new LolliPop(args);
        Present present = new Present();
        new MyFrame(present);
    }
}
