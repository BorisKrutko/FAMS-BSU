package LW11p2;

import java.io.File;

public class MyCollectionUtillStreamIP extends MyCollection implements MyCollectionUtill{
    @Override
    public String mapToString() {
        StringBuilder resStrBuilder = new StringBuilder();
        this.sortedCollectioStudents.entrySet().forEach(entry -> {
            resStrBuilder.append(entry.toString());
        });
        return resStrBuilder.toString();
    }

    @Override
    public String mapToStringAtRate(int rate) {
        StringBuilder resStrBuilder = new StringBuilder();
        this.sortedCollectioStudents.entrySet().stream().filter(entry -> entry.getValue().getCourseNumber() == rate)
        .forEach(entry -> {
            resStrBuilder.append(entry.toString());
        });
        return resStrBuilder.toString();
    }

    @Override
    public String listToString() {
        StringBuilder resStr = new StringBuilder();
        this.collectioStudents.forEach(value -> {
            resStr.append(value.toString());
        });
        return resStr.toString();
    }

    public MyCollectionUtillStreamIP(File fName) {
        super(fName);
    }

    public MyCollectionUtillStreamIP() {}
}
