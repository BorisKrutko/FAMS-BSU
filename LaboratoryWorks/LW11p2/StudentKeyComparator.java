package LW11p2;

import java.util.Comparator;

public class StudentKeyComparator implements Comparator<StudentKey> {
    @Override
    public int compare(StudentKey key1, StudentKey key2) {
        int groupComparsion = key1.getGroupNumber().compareTo(key2.getGroupNumber());
        if (groupComparsion != 0) {
            return groupComparsion;
        }
        else {
            return key1.getSurname().compareTo(key2.getSurname());
        }
    }
}
