package LW7;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("part1 or part2 (input 1 or 2): ");
        String inputString = scanner.nextLine();
        switch (inputString) {
            case "1":
                new MyFramePart1();
                break;
            case "2":
                new MyFramePart2();
                break;
            default:
                throw new AssertionError();
        }
    }
}
