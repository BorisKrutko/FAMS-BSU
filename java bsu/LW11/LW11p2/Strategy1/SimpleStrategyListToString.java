package LW11p2.Strategy1;

import Strategy1.StrategyListToString;

import java.util.ArrayList;

import Student;

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
