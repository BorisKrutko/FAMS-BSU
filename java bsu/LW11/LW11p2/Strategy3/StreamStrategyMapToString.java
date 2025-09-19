package LW11p2.Strategy3;

import java.util.TreeMap;

import LW11p2.Student;
import LW11p2.StudentKey;

public class StreamStrategyMapToString implements StrategyMapToString {
    public StreamStrategyMapToString() {}

    @Override
    public String mapToString(TreeMap<StudentKey, Student> sortedCollectioStudents) {
        StringBuilder resStrBuilder = new StringBuilder();
        sortedCollectioStudents.entrySet().forEach(entry -> {
            resStrBuilder.append(entry.toString());
        });
        return resStrBuilder.toString();
    }
}
