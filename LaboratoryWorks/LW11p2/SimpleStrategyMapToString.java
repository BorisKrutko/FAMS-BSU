package LW11p2;

import java.util.Map;
import java.util.TreeMap;

public class SimpleStrategyMapToString implements StrategyMapToString {
    public SimpleStrategyMapToString() {}

    @Override
    public String mapToString(TreeMap<StudentKey, Student> sortedCollectioStudents) {
        StringBuilder resStrBuilder = new StringBuilder();
        for (Map.Entry<StudentKey, Student> entry : sortedCollectioStudents.entrySet()) {
            resStrBuilder.append(entry.toString());
        }
        return resStrBuilder.toString();
    }
}
