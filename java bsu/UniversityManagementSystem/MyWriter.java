package UniversityManagementSystem;

import java.io.FileWriter;
import java.io.IOException;

public class MyWriter {
    public MyWriter() {}
    
    public void saveCouseToFile(String fileName, Course course) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(course.toString());
        } catch (IOException e) {}
    }
}
