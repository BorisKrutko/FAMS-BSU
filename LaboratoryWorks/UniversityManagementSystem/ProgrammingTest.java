package UniversityManagementSystem;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class ProgrammingTest {
    public static void main(String[] args) {
        Set<Student> students = new HashSet<>();
        students.add(new Student("Ivan", "ivan1", "ivan@mail.com"));
        students.add(new Student("Ann", "ann2", "ann@mail.com"));
        students.add(new Student("Max", "max3", "max@mail.com"));
        Course course = new Course(students, "Mathematical modeling");
        System.out.println(course.toString());

        for (Student i : course) {
            System.out.printf("%-10s%20s%n", i.getName(), i.getEmail());
        }

        Course fpmi = new Course(students, "fpmi");
        fpmi.addStudent(new Student("Ivan", "ivan", "ivan@mail.com"));
        Predicate<Student> predicate = student -> student.getLogin().length() < 4;
        fpmi.filterStudents(predicate);
        MyWriter writer = new MyWriter();
        writer.saveCouseToFile("output", fpmi);
    }
}
