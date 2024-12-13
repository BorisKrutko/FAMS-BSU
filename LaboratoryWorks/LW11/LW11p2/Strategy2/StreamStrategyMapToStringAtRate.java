package LW11p2.Strategy2;

import java.util.TreeMap;

import LW11p2.Student;
import LW11p2.StudentKey;

public class StreamStrategyMapToStringAtRate implements StrategyMapToStringAtRate {

    public StreamStrategyMapToStringAtRate() {}

    @Override
    public String mapToStringAtRate(TreeMap<StudentKey, Student> sortedCollectioStudents, int rate) {
        StringBuilder resStrBuilder = new StringBuilder();
        sortedCollectioStudents.entrySet().stream().filter(entry -> entry.getValue().getCourseNumber() == rate)
        .forEach(entry -> {
            resStrBuilder.append(entry.toString());
        });
        return resStrBuilder.toString();
    }    
}
