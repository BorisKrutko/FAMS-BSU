package KW1;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
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
    ArrayList<Candy> unsortedCandies = new ArrayList<>();
    Set<Candy> sortedCandies = new HashSet<>();
    String[] argsNewCandy;

    public Present(File file) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                argsNewCandy = scanner.nextLine().split(" ");
                switch (CandyType.fromCode(argsNewCandy[0])) {
                    case CandyType.LOLLIPOP:
                        unsortedCandies.add(new LolliPop(argsNewCandy));
                        break;
                    case CandyType.CHOCOLATE:
                        sortedCandies.add(new Chocolate(argsNewCandy));
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        } catch (FileNotFoundException e) { System.out.println(e.getMessage()); }
    }

    public void sort(Comparator<Candy> comparator) {
        sortedCandies = sortedCandies.stream().sorted(comparator).collect(Collectors.toSet());
    }

    public ArrayList<Candy> find(Predicate<Candy> predicate) {
        return sortedCandies.stream().filter(predicate)
                                    .collect(Collectors.toCollection(ArrayList::new));
    } 

    public String arrayListToString() {
        StringBuffer stringBuffer = new StringBuffer();
        unsortedCandies.forEach(el -> stringBuffer.append(el.toString()));
        return stringBuffer.toString();
    }

    public String setToString() {
        StringBuffer stringBuffer = new StringBuffer();
        sortedCandies.forEach(el -> stringBuffer.append(el.toString()));
        return stringBuffer.toString();
    }

    public void add(String[] args) {
        switch (CandyType.fromCode(args[0])) {
            case CandyType.LOLLIPOP:
                unsortedCandies.add(new LolliPop(args));
                break;
            case CandyType.CHOCOLATE:
                sortedCandies.add(new Chocolate(args));
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
