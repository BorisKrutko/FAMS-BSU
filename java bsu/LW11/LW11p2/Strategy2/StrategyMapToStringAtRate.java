package LW11p2.Strategy2;

import java.util.TreeMap;

import LW11p2.Student;
import LW11p2.StudentKey;

public interface StrategyMapToStringAtRate {
    public String mapToStringAtRate(TreeMap<StudentKey, Student> sortedCollectioStudents, int rate);
}
