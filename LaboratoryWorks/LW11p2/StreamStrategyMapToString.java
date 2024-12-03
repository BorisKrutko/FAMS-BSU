package LW11p2;

import java.util.TreeMap;

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
