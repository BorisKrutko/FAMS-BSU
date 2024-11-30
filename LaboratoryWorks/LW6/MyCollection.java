package LW6;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class MyCollection{
    TreeMap<StudentKey, Student> sortedCollectioStudents = new TreeMap<>(new StudentKeyComparator());
    ArrayList<Student> collectioStudents = new ArrayList<>();

    public MyCollection (){}
    
    public MyCollection (File fName){
        try{
            String str;
            String[] tokens;
            Scanner scanner = new Scanner(fName);
            while (scanner.hasNextLine()) {
                str = scanner.nextLine();
                tokens = str.split(" ");
                Student temp = new Student(tokens);
                collectioStudents.add(temp);
                sortedCollectioStudents.put(new StudentKey(temp.getGroupNumber(), temp.getSurname()), temp);
            }
        } catch(Exception e) {System.out.println("file not found");}
    }

    public String mapToString() {
        StringBuilder resStrBuilder = new StringBuilder();
        this.sortedCollectioStudents.entrySet().forEach(entry -> {
            resStrBuilder.append(entry.toString());
        });
        return resStrBuilder.toString();
    }

    public String mapToStringAtRate(int rate) {
        StringBuilder resStrBuilder = new StringBuilder();
        this.sortedCollectioStudents.entrySet().stream().filter(entry -> entry.getValue().getCourseNumber() == rate)
        .forEach(entry -> {
            resStrBuilder.append(entry.toString());
        });
        return resStrBuilder.toString();
    }

    public String listToString() {
        StringBuilder resStr = new StringBuilder();
        this.collectioStudents.forEach(value -> {
            resStr.append(value.toString());
        });
        return resStr.toString();
    }
    
    public void add(Student student) {
        sortedCollectioStudents.put(new StudentKey(student.getGroupNumber(), student.getSurname()), student);
        collectioStudents.add(student);
    } 
}
