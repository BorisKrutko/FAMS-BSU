package LW11p2;

import java.io.File;
import java.util.Map;

public class MyCollectionUtillSimle extends MyCollection implements MyCollectionUtill {
    @Override
    public String mapToString() {
        StringBuilder resStrBuilder = new StringBuilder();
        for (Map.Entry<StudentKey, Student> entry : this.sortedCollectioStudents.entrySet()) {
            resStrBuilder.append(entry.toString());
        }
        return resStrBuilder.toString();
    }

    @Override
    public String mapToStringAtRate(int rate) {
        StringBuilder resStrBuilder = new StringBuilder();
        for (Map.Entry<StudentKey, Student> entry : this.sortedCollectioStudents.entrySet()) {
            if (entry.getValue().getCourseNumber() == rate) {
                resStrBuilder.append(entry.toString());
            }
        }
        return resStrBuilder.toString();
    }

    @Override
    public String listToString() {
        StringBuilder resStr = new StringBuilder();
        for (Student value : this.collectioStudents) {
            resStr.append(value.toString());
        }
        return resStr.toString();
    }

    public MyCollectionUtillSimle(File fName) {
        super(fName);
    }

    public MyCollectionUtillSimle() {}
}
