package KW1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

enum CandyType {
    LOLLIPOP("L"), CHOCOLATE("C");

    private String code;

    CandyType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static CandyType fromCode(String code) {
        for (CandyType type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
}

public class Present {
    private ArrayList<Candy> unsortedCandies = new ArrayList<>();
    private List<Candy> sortedCandies = new ArrayList<>();
    String[] argsNewCandy;

    public Present(){}
    
    public Present(File file) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                argsNewCandy = scanner.nextLine().split(" ");
                this.add(argsNewCandy);
            }
        } catch (FileNotFoundException e) { System.out.println(e.getMessage()); }
    }

    public void sort(Comparator<Candy> comparator) {
        sortedCandies = sortedCandies.stream().sorted(comparator).collect(Collectors.toList());
    }

    public ArrayList<Candy> find(Predicate<Candy> predicate) {
        return unsortedCandies.stream().filter(predicate)
                                    .collect(Collectors.toCollection(ArrayList::new));
    } 

    public String arrayListToString() {
        StringBuffer stringBuffer = new StringBuffer();
        unsortedCandies.forEach(el -> stringBuffer.append(el.toString()).append("<br>"));
        return stringBuffer.toString();
    }

    public String ListToString() {
        StringBuffer stringBuffer = new StringBuffer();
        sortedCandies.forEach(el -> stringBuffer.append(el.toString()).append("<br>"));
        return stringBuffer.toString();
    }

    public void add(String[] args) {
        switch (CandyType.fromCode(args[0])) {
            case CandyType.LOLLIPOP:
                LolliPop newLolliPop = new LolliPop(args);
                unsortedCandies.add(newLolliPop);
                sortedCandies.add(newLolliPop);
                break;
            case CandyType.CHOCOLATE:
                Chocolate newChocolate = new Chocolate(args);
                sortedCandies.add(newChocolate);
                unsortedCandies.add(newChocolate);
                break;
            default:
                throw new AssertionError();
        }
    }

    public HashMap<Double, Candy> toMapWaightCandy() {
        HashMap<Double, Candy> unser = new HashMap<>();
        unsortedCandies.forEach(el -> unser.put(el.getWaight(), el));
        return unser;
    }
}
