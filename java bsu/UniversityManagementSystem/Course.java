package UniversityManagementSystem;

import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Course implements Iterable<Student> {
    private Set<Student> students;
    private String course;

    public Course(Set<Student> students, String course) {
        this.students = students;
        this.course = course;
    }

    public void addStudent(Student studentToAdd) {
        students.add(studentToAdd);
    }
    
    public Set<Student> filterStudents(Predicate<Student> predicate) {
        return this.students.stream().filter(predicate).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        StringBuffer resStr = new StringBuffer();
        students.forEach(student -> resStr.append(student.toString()));
        return resStr.toString();
    }

    @Override
    public Iterator<Student> iterator() {
        return students.iterator();
    }
}
