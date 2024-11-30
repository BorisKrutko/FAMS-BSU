package LW11p2;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class MyCollection{
    protected TreeMap<StudentKey, Student> sortedCollectioStudents = new TreeMap<>(new StudentKeyComparator());
    protected ArrayList<Student> collectioStudents = new ArrayList<>();

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
    
    public void add(Student student) {
        sortedCollectioStudents.put(new StudentKey(student.getGroupNumber(), student.getSurname()), student);
        collectioStudents.add(student);
    } 
}
