package LW11p2;

import LW11p2.Strategy1.SimpleStrategyListToString;
import LW11p2.Strategy1.StrategyListToString;
import LW11p2.Strategy1.StreamStrategyListToString;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class MyCollection{
    private TreeMap<StudentKey, Student> sortedCollectioStudents = new TreeMap<>(new StudentKeyComparator());
    private ArrayList<Student> collectioStudents = new ArrayList<>();
    private StrategyListToString strategyListToString;
    private StrategyMapToString strategyMapToString;
    private StrategyMapToStringAtRate strategyMapToStringAtRate;
    
    public void setSimpleMapToString() {
        this.strategyMapToString = new SimpleStrategyMapToString();
    }
    
    public void setStreamMapToString() {
        this.strategyMapToString = new StreamStrategyMapToString();
    }

    public void setStreamMapToStringAtRate() {
        this.strategyMapToStringAtRate = new StreamStrategyMapToStringAtRate();
    }

    public void setSimpleMapToStringAtRate() {
        this.strategyMapToStringAtRate = new SimpleStrategyMapToStringAtRate();
    }

    public void setSimpleListToString() {
        this.strategyListToString = new SimpleStrategyListToString();
    }

    public void setStreamListToString() {
        this.strategyListToString = new StreamStrategyListToString();
    }   
    
    public String listToString() {
        return this.strategyListToString.listToString(this.collectioStudents);
    }

    public String mapToStringAtRate(int rate) {
        return this.strategyMapToStringAtRate.mapToStringAtRate(this.sortedCollectioStudents, rate);
    }

    public String mapToString() {
        return this.strategyMapToString.mapToString(this.sortedCollectioStudents);
    }


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
