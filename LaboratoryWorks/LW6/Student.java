package LW6;

public class Student {
    int idNumber;
    String surname;
    int groupNumber;
    int courseNumber;

    public Student(int idNumber, String surname, int groupNumber, int courseNumber) {
        this.idNumber = idNumber;
        this.surname = surname;
        this.groupNumber = groupNumber;
        this.courseNumber = courseNumber;
    }
    
    public Student(String[] args) {
        try {
            if (args.length != 4) {
                throw new Exception("i cant creat student");
            }
            this.idNumber = Integer.parseInt(args[0]);
            this.surname = args[1];
            this.groupNumber = Integer.parseInt(args[2]);
            this.courseNumber = Integer.parseInt(args[3]);
        } catch (Exception e) {}  
    } 
    
    public int getIdNumber(){
        return this.idNumber;
    }

    public int getGroupNumber(){
        return this.groupNumber;
    }

    public String getSurname(){
        return this.surname;
    }

    public int getCourseNumber(){
        return this.courseNumber;
    }
}

