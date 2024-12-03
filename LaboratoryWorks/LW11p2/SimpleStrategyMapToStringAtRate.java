package LW11p2;

import java.util.Map;
import java.util.TreeMap;

public class SimpleStrategyMapToStringAtRate implements StrategyMapToStringAtRate{

public SimpleStrategyMapToStringAtRate() {}

@Override
    public String mapToStringAtRate(TreeMap<StudentKey, Student> sortedCollectioStudents, int rate) {
        StringBuilder resStrBuilder = new StringBuilder();
        for (Map.Entry<StudentKey, Student> entry : sortedCollectioStudents.entrySet()) {
            if (entry.getValue().getCourseNumber() == rate) {
                resStrBuilder.append(entry.toString());
            }
        }
        return resStrBuilder.toString();
    }    
}
