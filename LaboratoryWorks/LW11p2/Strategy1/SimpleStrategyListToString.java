package LW11p2.Strategy1;

import LW11p2.Student;
import java.util.ArrayList;

public class SimpleStrategyListToString implements StrategyListToString {
    @Override
    public String listToString(ArrayList<Student> collectioStudents) {
        StringBuilder resStr = new StringBuilder();
        for (Student value : collectioStudents) {
            resStr.append(value.toString());
        }
        return resStr.toString();
    }
}
