package LW11p2.Strategy1;

import java.util.ArrayList;

import Student;
import Strategy1.StrategyListToString;

public class StreamStrategyListToString implements StrategyListToString {
    @Override
    public String listToString(ArrayList<Student> collectioStudents) {
        StringBuilder resStr = new StringBuilder();
        collectioStudents.forEach(value -> {
            resStr.append(value.toString());
        });
        return resStr.toString();
    }    
}
