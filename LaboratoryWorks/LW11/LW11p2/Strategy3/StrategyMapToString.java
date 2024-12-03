package LW11p2.Strategy3;

import java.util.TreeMap;

import LW11p2.Student;
import LW11p2.StudentKey;


public interface StrategyMapToString {
    public String mapToString(TreeMap<StudentKey, Student> sortedCollectioStudents);
}
