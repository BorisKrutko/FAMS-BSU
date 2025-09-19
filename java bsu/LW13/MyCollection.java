package LW13;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class MyCollection{
    private ArrayList<Student> collectioStudents;

    public ArrayList<Student> getCollectionStudents() { return  this.collectioStudents; }
    
    public MyCollection () {
        collectioStudents = new ArrayList<>();  
    }
    
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
            }
        } catch(Exception e) {System.out.println("file not found");}
    }
    
    public void add(Student student) {
        collectioStudents.add(student);
    } 
}
